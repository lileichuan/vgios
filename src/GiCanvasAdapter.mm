//! \file GiCanvasAdapter.mm
//! \brief 实现画布适配器类 GiCanvasAdapter
// Copyright (c) 2012-2013, https://github.com/rhcad/touchvg

#import "GiImageCache.h"
#import "NSString+Drawing.h"
#include "GiCanvasAdapter.h"

static const float patDash[]      = { 4, 2, 0 };
static const float patDot[]       = { 1, 2, 0 };
static const float patDashDot[]   = { 10, 2, 2, 2, 0 };
static const float dashDotdot[]   = { 20, 2, 2, 2, 2, 2, 0 };
const float* const GiCanvasAdapter::LINEDASH[] = { NULL, patDash, patDot, patDashDot, dashDotdot };

GiCanvasAdapter::GiCanvasAdapter(GiImageCache *cache) : _ctx(NULL), _cache(cache), _gradient0(NULL)
{
}

GiCanvasAdapter::~GiCanvasAdapter()
{
}

CGContextRef GiCanvasAdapter::context()
{
    return _ctx;
}

bool GiCanvasAdapter::beginPaint(CGContextRef context, bool fast)
{
    if (_ctx || !context) {
        return false;
    }
    
    _ctx = context;
    _fill = false;
    _gradient = NULL;
    
    CGContextSetShouldAntialias(_ctx, true);       // 两者都为true才反走样
    CGContextSetAllowsAntialiasing(_ctx, true);
    
    CGContextSetFlatness(_ctx, fast ? 10 : 3);      // 平滑度为3达到精确和速度的平衡点
    CGContextSetInterpolationQuality(_ctx, kCGInterpolationLow);
    
    CGContextSetLineCap(_ctx, kCGLineCapRound);     // 圆端
    CGContextSetLineJoin(_ctx, kCGLineJoinRound);   // 折线转角圆弧过渡
    
    CGContextSetRGBFillColor(_ctx, 0, 0, 0, 0);     // 默认不填充
    
    return true;
}

void GiCanvasAdapter::endPaint()
{
    _ctx = NULL;
    if (_gradient0) {
        CGGradientRelease(_gradient0);
        _gradient0 = NULL;
    }
}

float GiCanvasAdapter::colorPart(int argb, int byteOrder)
{
    return (float)((argb >> (byteOrder * 8)) & 0xFF) / 255.f;
}

void GiCanvasAdapter::setPen(int argb, float width, int style, float phase, float)
{
    if (argb != 0) {
        CGContextSetRGBStrokeColor(_ctx, colorPart(argb, 2), colorPart(argb, 1),
                                   colorPart(argb, 0), colorPart(argb, 3));
    }
    if (width > 0) {
        CGContextSetLineWidth(_ctx, width);
    }
    if (style >= 0) {
        int linecap = style & kLineCapMask;
        
        style = style & kLineDashMask;
        if (style > 0 && style < 5) {
            CGFloat pattern[6];
            int n = 0;
            for (; LINEDASH[style][n] > 0.1f; n++) {
                pattern[n] = LINEDASH[style][n] * (width < 1.f ? 1.f : width);
            }
            CGContextSetLineDash(_ctx, phase, pattern, n);
        }
        else if (0 == style) {
            CGContextSetLineDash(_ctx, 0, NULL, 0);
        }
        if (linecap & kLineCapButt)
            CGContextSetLineCap(_ctx, kCGLineCapButt);
        else if (linecap & kLineCapRound)
            CGContextSetLineCap(_ctx, kCGLineCapRound);
        else if (linecap & kLineCapSquare)
            CGContextSetLineCap(_ctx, kCGLineCapSquare);
        else {
            CGContextSetLineCap(_ctx, (style > 0 && style < 5)
                                ? kCGLineCapButt : kCGLineCapRound);
        }
    }
}

void GiCanvasAdapter::setBrush(int argb, int style)
{
    if (0 == style) {
        float alpha = colorPart(argb, 3);
        _fill = alpha > 1e-2f;
        CGContextSetRGBFillColor(_ctx, colorPart(argb, 2), colorPart(argb, 1),
                                 colorPart(argb, 0), alpha);
    }
}

void GiCanvasAdapter::saveClip()
{
    CGContextSaveGState(_ctx);
}

void GiCanvasAdapter::restoreClip()
{
    CGContextRestoreGState(_ctx);
}

void GiCanvasAdapter::clearRect(float x, float y, float w, float h)
{
    CGContextClearRect(_ctx, CGRectMake(x, y, w, h));
}

void GiCanvasAdapter::drawRect(float x, float y, float w, float h, bool stroke, bool fill)
{
    if (fill && _fill) {
        CGContextFillRect(_ctx, CGRectMake(x, y, w, h));
    }
    if (stroke) {
        CGContextStrokeRect(_ctx, CGRectMake(x, y, w, h));
    }
}

bool GiCanvasAdapter::clipRect(float x, float y, float w, float h)
{
    CGContextClipToRect(_ctx, CGRectMake(x, y, w, h));
    CGRect rect = CGContextGetClipBoundingBox(_ctx);
    return !CGRectIsEmpty(rect);
}

void GiCanvasAdapter::drawLine(float x1, float y1, float x2, float y2)
{
    CGContextBeginPath(_ctx);
    CGContextMoveToPoint(_ctx, x1, y1);
    CGContextAddLineToPoint(_ctx, x2, y2);
    CGContextStrokePath(_ctx);
}

void GiCanvasAdapter::drawEllipse(float x, float y, float w, float h, bool stroke, bool fill)
{
    if (fill && _fill) {
        CGContextFillEllipseInRect(_ctx, CGRectMake(x, y, w, h));
    }
    if (stroke) {
        CGContextStrokeEllipseInRect(_ctx, CGRectMake(x, y, w, h));
    }
}

void GiCanvasAdapter::beginPath()
{
    CGContextBeginPath(_ctx);
}

void GiCanvasAdapter::moveTo(float x, float y)
{
    CGContextMoveToPoint(_ctx, x, y);
}

void GiCanvasAdapter::lineTo(float x, float y)
{
    CGContextAddLineToPoint(_ctx, x, y);
}

void GiCanvasAdapter::bezierTo(float c1x, float c1y, float c2x, float c2y, float x, float y)
{
    CGContextAddCurveToPoint(_ctx, c1x, c1y, c2x, c2y, x, y);
}

void GiCanvasAdapter::quadTo(float cpx, float cpy, float x, float y)
{
    CGContextAddQuadCurveToPoint(_ctx, cpx, cpy, x, y);
}

void GiCanvasAdapter::closePath()
{
    CGContextClosePath(_ctx);
}

void GiCanvasAdapter::drawPath(bool stroke, bool fill)
{
    if (!stroke && !fill) {
        return; // can used to clip later
    }
    if (_gradient && !CGContextIsPathEmpty(_ctx)) {
        CGContextSaveGState(_ctx);
        CGRect rect = CGContextGetPathBoundingBox(_ctx);
        CGContextReplacePathWithStrokedPath(_ctx);
        CGContextClip(_ctx);
        
        CGContextDrawLinearGradient(_ctx, _gradient, CGPointMake(CGRectGetMinX(rect), CGRectGetMinY(rect)),
                                    CGPointMake(CGRectGetMaxX(rect), CGRectGetMaxY(rect)), 0);
        CGContextRestoreGState(_ctx);
    }
    else {
        fill = fill && _fill;
        CGContextDrawPath(_ctx, (stroke && fill) ? kCGPathEOFillStroke
                          : (fill ? kCGPathEOFill : kCGPathStroke));  // will clear the path
    }
}

bool GiCanvasAdapter::clipPath()
{
    CGContextClip(_ctx);
    CGRect rect = CGContextGetClipBoundingBox(_ctx);
    return !CGRectIsEmpty(rect);
}

bool GiCanvasAdapter::drawHandle(float x, float y, int type)
{
    if (type >= 0) {
        NSString *name;
        
        if (type < 6) {
            NSString *names[] = { @"vgdot1.png", @"vgdot2.png", @"vgdot3.png",
                @"vg_lock.png", @"vg_unlock.png", @"vg_back.png", @"vg_endedit.png" };
            name = [@"TouchVG.bundle" stringByAppendingPathComponent:names[type]];
        } else {
            name = [NSString stringWithFormat:@"vgdot%d.png", type];
        }
        
        UIImage *image = _cache ? [_cache loadImage:name] : [UIImage imageNamed:name];
        
        if (image) {
            CGImageRef img = [image CGImage];
            float w = CGImageGetWidth(img) / image.scale;
            float h = CGImageGetHeight(img) / image.scale;
            
            CGAffineTransform af = CGAffineTransformMake(1, 0, 0, -1, x - w * 0.5f, y + h * 0.5f);
            CGContextConcatCTM(_ctx, af);
            CGContextDrawImage(_ctx, CGRectMake(0, 0, w, h), img);
            CGContextConcatCTM(_ctx, CGAffineTransformInvert(af));
            
            return true;
            // 如果使用下面一行显示，则图像是上下颠倒的:
            // CGContextDrawImage(_ctx, CGRectMake(x - w * 0.5f, y - h * 0.5f, w, h), img);
        }
    }
    
    return false;
}

bool GiCanvasAdapter::drawBitmap(const char* name, float xc, float yc,
                                 float w, float h, float angle)
{
    UIImage *image = (name && _cache ? [_cache loadImage:[NSString stringWithUTF8String:name]]
                      : [UIImage imageNamed:@"app57.png"]);
    if (image) {
        CGImageRef img = [image CGImage];
        CGAffineTransform af = CGAffineTransformMake(1, 0, 0, -1, xc, yc);
        af = CGAffineTransformRotate(af, angle);
        CGContextConcatCTM(_ctx, af);
        CGContextDrawImage(_ctx, CGRectMake(-w/2, -h/2, w, h), img);
        CGContextConcatCTM(_ctx, CGAffineTransformInvert(af));
    }
    
    return !!image;
}

NSString *GiLocalizedString(NSString *name)
{
    NSString *path = [[NSBundle mainBundle] pathForResource:@"TouchVG" ofType:@"bundle"];
    NSString *str = NSLocalizedStringFromTableInBundle(name, nil, [NSBundle bundleWithPath:path], nil);
    return [str isEqualToString:name] ? NSLocalizedString(name, nil) : str;
}

float GiCanvasAdapter::drawTextAt(const char* text, float x, float y, float h, int align)
{
    UIGraphicsPushContext(_ctx);        // 设置为当前上下文，供UIKit显示使用
    
    NSString *str;
    
    if (*text == '@') {
        str = GiLocalizedString([NSString stringWithUTF8String:text+1]);
    } else {
        str = [[NSString alloc] initWithUTF8String:text];
    }
    
    // 实际字体大小 = 目标高度 h * 临时字体大小 h / 临时字体行高 actsize.height
    UIFont *font = [UIFont systemFontOfSize:h]; // 以像素点高度作为字体大小得到临时字体
    CGSize actsize = [str boundingRectWithSize:CGSizeMake(1e4f, h)  // 限制单行高度
                                       options:NSStringDrawingTruncatesLastVisibleLine
                                    attributes:@{NSFontAttributeName:font}  // 使用临时字体计算文字显示宽高
                                       context:nil].size;
    font = [UIFont systemFontOfSize: h * h / actsize.height];
    actsize = [str sizeWithAttributes:@{NSFontAttributeName:font}]; // 文字实际显示的宽高
    
    x -= (align == 2) ? actsize.width : ((align == 1) ? actsize.width / 2 : 0);
    [str drawAtPoint:CGPointMake(x, y) withAttributes:@{NSFontAttributeName:font}];  // 显示文字
    if (*text != '@')
        [str RELEASE];
    
    UIGraphicsPopContext();
    
    return actsize.width;
}
