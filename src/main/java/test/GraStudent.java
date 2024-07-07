package test;

public class GraStudent extends Student {
    public String degree;

    @Override
    public void show() {
        System.out.printf("name: %s\nage: %s\ndegree: %s\n",name , age , degree);
    }

    public static void main(String[] args) {
        GraStudent gs = new GraStudent();
        gs.name = "徐涛";
        gs.age = 20;
        gs.degree = "院士";
        gs.show();
    }
}