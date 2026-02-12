import java.util.ArrayList;
import java.util.List;

class MidpointCircleRasterizer implements LineRasterizer {

    @Override
    public Point[] rasterize(Point p1, Point p2) {

        int cx = (p1.x + p2.x) / 2;
        int cy = (p1.y + p2.y) / 2;

        int dx = p2.x - p1.x;
        int dy = p2.y - p1.y;
        int r = (int) Math.round(Math.sqrt(dx * dx + dy * dy) / 2.0);

        List<Point> points = new ArrayList<>();

        int x = 0;
        int y = r;
        int d = 1 - r;

        while (x <= y) {
            plot(points, cx, cy, x, y);

            if (d < 0) {
                d += 2 * x + 3;
            } else {
                d += 2 * (x - y) + 5;
                y--;
            }
            x++;
        }

        return points.toArray(new Point[0]);
    }

    private void plot(List<Point> pts, int cx, int cy, int x, int y) {
        pts.add(new Point(cx + x, cy + y));
        pts.add(new Point(cx - x, cy + y));
        pts.add(new Point(cx + x, cy - y));
        pts.add(new Point(cx - x, cy - y));
        pts.add(new Point(cx + y, cy + x));
        pts.add(new Point(cx - y, cy + x));
        pts.add(new Point(cx + y, cy - x));
        pts.add(new Point(cx - y, cy - x));
    }
}
