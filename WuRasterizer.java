import java.util.ArrayList;
import java.util.List;

class WuRasterizer implements LineRasterizer {

    @Override
    public Point[] rasterize(Point p1, Point p2) {

        List<Point> points = new ArrayList<>();

        int x1 = p1.x;
        int y1 = p1.y;
        int x2 = p2.x;
        int y2 = p2.y;

        boolean steep = Math.abs(y2 - y1) > Math.abs(x2 - x1);

        if (steep) {
            // swap x and y
            int t;
            t = x1; x1 = y1; y1 = t;
            t = x2; x2 = y2; y2 = t;
        }

        if (x1 > x2) {
            // swap endpoints
            int t;
            t = x1; x1 = x2; x2 = t;
            t = y1; y1 = y2; y2 = t;
        }

        int dx = x2 - x1;
        int dy = y2 - y1;

        float gradient = dx == 0 ? 1 : (float) dy / dx;

        float y = y1;

        for (int x = x1; x <= x2; x++) {
            int iy = (int) Math.floor(y);

            if (steep) {
                points.add(new Point(iy, x));
                points.add(new Point(iy + 1, x));
            } else {
                points.add(new Point(x, iy));
                points.add(new Point(x, iy + 1));
            }

            y += gradient;
        }

        return points.toArray(new Point[0]);
    }
}
