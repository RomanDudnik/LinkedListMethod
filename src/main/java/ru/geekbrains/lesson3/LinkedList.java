package ru.geekbrains.lesson3;

import java.util.Comparator;

/**
 * Связный список
 * @param <T>
 */
public class LinkedList<T> {

    /**
     * Указатель на первый элемент (узел) связного списка
     */
    private Node head;

    /**
     * Узел (элемент)
     */
    public class Node{

        /**
         * Указатель на следующий узел
         */
        public Node next;

        /**
         * Значение узла
         */
        public T value;

    }

    /**
     * Добавление нового элемента в начало связного списка
     * @param value значение
     */
    public void addFirst(T value){
        Node node = new Node();
        node.value = value;
        if (head != null){
            node.next = head;
        }
        head = node;
    }

    /**
     * Удалить первый элемент связного списка
     */
    public void removeFirst(){
        if (head != null){
            head = head.next;
        }
    }

    /**
     * Поиск элемента в связном списке по значению
     * @param value значение
     * @return элемент (узел)
     */
    public Node contains(T value){
        Node node = head;
        while (node != null){
            if (node.value.equals(value))
                return node;
            node = node.next;
        }
        return null;
    }

    /**
     * Сортировка
     * @param comparator
     */
    public void sort(Comparator<T> comparator){
        Node node = head;
        while (node != null){

            Node minValueNode = node;

            Node node2 = node.next;
            while (node2 != null){
                /*if (node2.value.compareTo(minValueNode.value) < 0) {
                    minValueNode = node2;
                }*/
                if (comparator.compare(minValueNode.value, node2.value) > 0){
                    minValueNode = node2;
                }
                node2 = node2.next;
            }

            if (minValueNode != node){
                T buf = node.value;
                node.value = minValueNode.value;
                minValueNode.value = buf;
            }

            node = node.next;
        }

    }

    /**
     * Добавление элемента в конец списка
     * @param value значение
     */
    public void addLast(T value){
        Node node = new Node();
        node.value = value;
        if (head == null){
            head = node;
        }
        else {
            Node lastNode = head;
            while (lastNode.next != null) {
                lastNode = lastNode.next;
            }
            lastNode.next = node;
        }
    }

    /**
     * Удаление элемента из конца связного списка
     */
    public void removeLast(){
        if (head == null)
            return;

        Node node = head;
        while (node.next != null){
            if (node.next.next == null){
                node.next = null;
                return;
            }
            node = node.next;
        }

        head = null;
    }


    @Override
    public String toString() {

        StringBuilder stringBuilder = new StringBuilder();

        Node node = head;
        while (node != null){
            stringBuilder.append(node.value);
            stringBuilder.append('\n');
            node = node.next;
        }

        return stringBuilder.toString();
    }

    /**
     * Метод reverse() - "разворот списка" работает по принципу перемещения ссылок между узлами в обратном порядке,
     * начиная с головы списка (head) и заканчивая последним узлом.
     * В результате выполнения метода, список будет развернут в обратном порядке.
     */

    public void reverse(){
        Node prev = null;       // Переменная `prev` будет использоваться для хранения предыдущего узла
        Node current = head;    // Переменная `current` будет использоваться для обхода списка и перестановки ссылок между узлами
        Node next =null;        //  Переменная `next` будет использоваться для сохранения ссылки на следующий узел перед изменением ссылки в текущем узле.

        // Цикл будет выполняться до тех пор, пока `current` не станет равным `null`. Это означает, что мы обошли все узлы.
        while (current != null) {
            next = current.next;    // Сохраняем ссылку на следующий узел в переменную `next`(чтобы не потерять доступ к следующему узлу)
            current.next = prev;    // Изменяем ссылку в текущем узле, чтобы она указывала на предыдущий узел.(разворачиваем ссылки в списке)
            prev = current;         // Обновляем значение `prev`, чтобы она указывала на текущий узел.(продвигаемся вперед по списку).
            current = next;         // Обновляем значение `current`, чтобы она указывала на следующий узел. (продолжаем обходить список).
        }
        head = prev;        // Обновляем значение `head`, чтобы оно указывало на последний узел после разворота списка.
                            // Т.к. `prev` указывает на последний узел после завершения цикла, мы делаем `head` равным `prev`.
    }

    /**
     * Метод reverseRecursive() - рекурсией
     */
    // Основной метод разворота списка, суда передаем список в качестве параметра и вызываем рекурсивный метод:
    public void reverseRecursive(){
        reverse(head);      // передаем ссылку на первый узел в рекурсивный метод reverse()
    }
    // Метод reverse() принимает в качестве параметра узел `node`, который является текущим узлом списка
    private void reverse(Node node){
        // Проверяем наличие узла, если null то мы достигли конца списка (или список пуст)
        if (node == null || node.next == null) {
            head = node;        // Обновляем голову списка, чтобы она указывала на последний узел
            return;
        }
        reverse(node.next);     // Вызываем функцию для следующего узла (рекурсивно)
        node.next.next = node;  // Меняем ссылку на следующий узел, чтобы она указывала на текущий узел
        node.next = null;       // Устанавливаем ссылку следующего узла на null для текущего узла
    }


}
