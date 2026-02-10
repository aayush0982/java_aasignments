package Day_21_arrays_generics;

public class Normal_ArrayList {
	public static void main() {
        MyArrayList list = new MyArrayList();

        list.add(70);
        list.add(50);
        list.add(40);
        list.add(10);
        list.add(20);
        list.add(30);
        list.add(40);
        list.add(50);
        list.add(60);

        list.print();
        System.out.println("\nindex : "+list.get(4));
        list.remove(3);
        list.print();
        System.out.println("\nElement at index : "+list.search(60));
        list.sort();
        list.print();
    }
}

class MyArrayList {
    private int[] data;
    private int size;
    public MyArrayList(){
        data=new int[4];
        size=0;
    }
    public void add(int value) {
        if (size == data.length) {
            resize();
        }
        data[size++] = value;
    }

    private void resize() {
        int[] newData = new int[data.length * 2];
        for (int i = 0; i < data.length; i++) {
            newData[i] = data[i];
        }
        data = newData;
    }
    public void print(){
        for(int i=0;i<size;i++){
            System.out.print(data[i]+" ");
        }
    }
    public int get(int index){
        if(index<0 || index>=size){
            throw new IndexOutOfBoundsException();
        }
        return data[index];
    }
    public void remove(int index) {
        if (index < 0 || index >= size) {
            return;
        }

        for (int i = index; i < size - 1; i++) {
            data[i] = data[i + 1];
        }

        size--;
        data[size] = 0;
    }
    public int search(int value){
        for(int i=0;i<size;i++){
            if(data[i]==value){
                return i;
            }
        }
        return -1;
    }
    public void sort(){
        for(int i=0;i<size-1;i++){
            for(int j=0;j<size-1-i;j++){
                if(data[j]>data[j+1]){
                    int t=data[j];
                    data[j]=data[j+1];
                    data[j+1]=t;
                }
            }
        }
    }
}

