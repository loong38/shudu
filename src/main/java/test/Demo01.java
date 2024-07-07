package test;
public class Demo01 implements 面积接口{
    @Override
    public double 获取圆形面积(double r) {
        double pai = 3.1415926;
        return pai*r*r;
    }

    @Override
    public double 获取方形面积(double a, double b) {
        return a*b;
    }

    @Override
    public double 获取三角形面积(double a, double b, double c) {
        double p = (a+b+c)/2;
        double S = Math.sqrt(p*(p-a)*(p-b)*(p-c));
        return S;
    }
}
