class Test {
    public static void main(String[] args) {
        IntegerSet i = new IntegerSet();
        i.insertElement(1);//
        i.insertElement(2);//
        i.insertElement(3);
        i.insertElement(5);//
        i.insertElement(5);
        i.insertElement(4);//
        System.out.println(i.toString());
        i.deleteElement(3);
        System.out.println(i.toString());
    }
}