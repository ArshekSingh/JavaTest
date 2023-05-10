package test.queue;

public class QueueEx1 {

    public static void main(String args[]) {
        Queue q1 = new Queue(5);
        q1.enqueue(1);
        q1.enqueue(2);
        q1.enqueue(3);
        q1.enqueue(4);
        q1.enqueue(5);
        for(int i=0;i<5;i++) {
            System.out.println(q1.show(i));
        }
        for(int i=0;i<6;i++) {
            System.out.println(q1.dequeue());
        }

    }

        public static class Queue {
            private int maxSize;
            private int[] queueArray;
            private int front;
            private int rear;
            private int nItems;

            public Queue(int size) {
                maxSize = size;
                queueArray = new int[maxSize];
                front = 0;
                rear = 0;
                nItems = 0;
            }

            public void enqueue(int item) {
                if (isFull()) {
                    System.out.println("Queue is full");
                    return;
                }
                queueArray[rear] = item;
                rear++;
                nItems++;
            }

            public int dequeue() {
                if (isEmpty()) {
                    System.out.println("Queue is empty");
                    return -1;
                }
                int temp = queueArray[front];
                front++;
                if (front == maxSize) {
                    front = 0;
                }
                nItems--;
                return temp;
            }

            public int peek() {
                if (isEmpty()) {
                    System.out.println("Queue is empty");
                    return -1;
                }
                return queueArray[front];
            }
            public int show(int index) {
                if(isEmpty()) {
                    System.out.println("Queue is empty");
                    return -1;
                }
                return queueArray[index];
            }

            public boolean isEmpty() {
                return (nItems == 0);
            }

            public boolean isFull() {
                return (nItems == maxSize);
            }
        }

    }
