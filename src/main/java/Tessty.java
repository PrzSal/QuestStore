public class Tessty {

    public static void main(String[] args) {

        String ff = "/login";
        String[] pairs = ff.split("/");
        System.out.println(pairs.length);
        for(String el: pairs)
            System.out.println("elem: " + el);
    }
}
