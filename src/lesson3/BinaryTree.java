package lesson3;

import kotlin.NotImplementedError;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;

// Attention: comparable supported but comparator is not
public class BinaryTree<T extends Comparable<T>> extends AbstractSet<T> implements CheckableSortedSet<T> {

    private static class Node<T> {
        final T value;

        Node<T> left = null;

        Node<T> right = null;

        Node(T value) {
            this.value = value;
        }
    }

    private Node<T> root = null;

    private int size = 0;

    private MyLovelySet tailSet;
    private MyLovelySet headSet;
    private MyLovelySet subSet;

    @Override
    public boolean add(T t) {
        Node<T> closest = find(t);
        int comparison = closest == null ? -1 : t.compareTo(closest.value);
        if (comparison == 0) {
            return false;
        }
        Node<T> newNode = new Node<>(t);
        if (closest == null) {
            root = newNode;
        } else if (comparison < 0) {
            assert closest.left == null;
            closest.left = newNode;
        } else {
            assert closest.right == null;
            closest.right = newNode;
        }
        size++;
        refreshSets();
        return true;
    }

    private void refreshSets() {
        if (tailSet != null) {
            tailSet(tailSet.getFromElement());
        }
        if (headSet != null) {
            headSet(headSet.getToElement());
        }
        if (subSet != null)
            subSet(subSet.getFromElement(), subSet.getToElement());
    }

    public boolean checkInvariant() {
        return root == null || checkInvariant(root);
    }

    public int height() {
        return height(root);
    }

    private boolean checkInvariant(Node<T> node) {
        Node<T> left = node.left;
        if (left != null && (left.value.compareTo(node.value) >= 0 || !checkInvariant(left))) return false;
        Node<T> right = node.right;
        return right == null || right.value.compareTo(node.value) > 0 && checkInvariant(right);
    }

    private int height(Node<T> node) {
        if (node == null) return 0;
        return 1 + Math.max(height(node.left), height(node.right));
    }

    /**
     * Удаление элемента в дереве
     * Средняя
     * Сложность O(n)
     * Доп.Память O(n)
     */
    @Override
    public boolean remove(Object o) {
        if (o == null || find((T) o) == null) {
            return false;
        } else {
            Node<T> node = remove(root, (T) o);
            if (node != null) {
                root = node;
                size--;
                refreshSets();
                return true;
            } else {
                return false;
            }
        }
    }

    private Node innerRemove(Node node) {
        if (node.right == null) {
            return node.left;
        }
        if (node.left == null) {
            return node.right;
        }
        Node t = node;
        node = minNode(t.right);
        node.right = deleteMin(t.right);
        node.left = t.left;
        return node;
    }

    private Node remove(Node<T> node, T value) {
        if (node == null) {
            return null;
        }
        if (value.compareTo(node.value) < 0) {
            node.left = remove(node.left, value);
        }
        if (value.compareTo(node.value) > 0) {
            node.right = remove(node.right, value);
        }
        if (node.value.compareTo(value) == 0) {
            node = innerRemove(node);
        }
        return node;
    }

    private Node deleteMin(Node node) {
        if (node.left == null) {
            return node.right;
        }
        node.left = deleteMin(node.left);
        return node;
    }

    private Node minNode(Node node) {
        Node currentNode = node;
        while (currentNode.left != null) {
            currentNode = currentNode.left;
        }
        return currentNode;
    }

    //Not works on adding 8 in set :(


    @Override
    public boolean contains(Object o) {
        @SuppressWarnings("unchecked")
        T t = (T) o;
        Node<T> closest = find(t);
        return closest != null && t.compareTo(closest.value) == 0;
    }

    private Node<T> find(T value) {
        if (root == null) return null;
        return find(root, value);
    }

    private Node<T> find(Node<T> start, T value) {
        int comparison = value.compareTo(start.value);
        if (comparison == 0) {
            return start;
        } else if (comparison < 0) {
            if (start.left == null) return start;
            return find(start.left, value);
        } else {
            if (start.right == null) return start;
            return find(start.right, value);
        }
    }

    public class BinaryTreeIterator implements Iterator<T> {

        Stack<Node> nodes;
        Node<T> currentNode = null;

        private BinaryTreeIterator() {
            nodes = new Stack<>();
            addLeftNodes(root);
        }

        private void addLeftNodes(Node<T> root) {
            while (root != null) {
                nodes.push(root);
                root = root.left;
            }
        }

        /**
         * Проверка наличия следующего элемента
         * Средняя
         * Сложность O(1)
         * Доп.Память O(1)
         */
        @Override
        public boolean hasNext() {
            return nodes.size() > 0;
        }

        /**
         * Поиск следующего элемента
         * Средняя
         * Сложность O(log(n))
         * Доп.Память O(Log(n))
         */
        @Override
        public T next() {
            currentNode = nodes.pop();
            if (currentNode.right != null) {
                addLeftNodes(currentNode.right);
            }
            return currentNode.value;
        }

        /**
         * Удаление следующего элемента
         * Сложная
         * Сложность O(1)
         * Доп.Память O(1)
         */
        @Override
        public void remove() {
            if (currentNode != null) {
                BinaryTree.this.remove(currentNode.value);
            }
        }
    }

    @NotNull
    @Override
    public Iterator<T> iterator() {
        return new BinaryTreeIterator();
    }

    @Override
    public int size() {
        return size;
    }


    @Nullable
    @Override
    public Comparator<? super T> comparator() {
        return null;
    }


    class MyLovelySet extends TreeSet {
        private T toElement;
        private T fromElement;

        public MyLovelySet(T toElement, T fromElement) {
            this.toElement = toElement;
            this.fromElement = fromElement;
        }

        public T getToElement() {
            return toElement;
        }

        public T getFromElement() {
            return fromElement;
        }

        public void setToElement(T toElement) {
            this.toElement = toElement;
        }

        public void setFromElement(T fromElement) {
            this.fromElement = fromElement;
        }

        @Override
        public boolean add(Object o) {
            T value = (T) o;
            if (toElement == null) {
                if (value.compareTo(fromElement) < 0) {
                    throw new IllegalArgumentException();
                } else {
                    BinaryTree.this.add(value);
                    super.add(value);
                    return true;
                }
            }
            if (fromElement == null) {
                if (value.compareTo(toElement) >= 0) {
                    throw new IllegalArgumentException();
                } else {
                    BinaryTree.this.add(value);
                    super.add(value);
                    return true;
                }
            }
            if (value.compareTo(toElement) < 0 && value.compareTo(fromElement) >= 0) {
                BinaryTree.this.add(value);
                super.add(value);
            } else {
                throw new IllegalArgumentException();
            }
            return true;
        }
    }


    /**
     * Для этой задачи нет тестов (есть только заготовка subSetTest), но её тоже можно решить и их написать
     * Очень сложная
     * Сложность O(n)
     * Доп.Память O(N)
     */
    @NotNull
    @Override
    public SortedSet<T> subSet(T fromElement, T toElement) {
        if (subSet == null) {
            subSet = new MyLovelySet(toElement, fromElement);
        }
        if (fromElement.compareTo(subSet.getFromElement()) != 0) {
            subSet.setFromElement(fromElement);
        }
        if (toElement.compareTo(subSet.getToElement()) != 0) {
            subSet.setToElement(toElement);
        }
        subSet.clear();
        for (T value : this) {
            if (value.compareTo(toElement) < 0 && value.compareTo(fromElement) >= 0) {
                subSet.add(value);
            }
        }
        return subSet;
    }

    /**
     * Найти множество всех элементов меньше заданного
     * Сложная
     * Сложность O(n)
     * Доп.Память O(N)
     */
    @NotNull
    @Override
    public SortedSet<T> headSet(T toElement) {
        if (headSet == null) {
            headSet = new MyLovelySet(toElement, null);
        }
        if (toElement.compareTo(headSet.getToElement()) != 0) {
            headSet.setToElement(toElement);
        }
        headSet.clear();
        for (T value : this) {
            if (value.compareTo(toElement) < 0) {
                headSet.add(value);
            } else {
                break;
            }
        }
        return headSet;
    }

    /**
     * Найти множество всех элементов больше или равных заданного
     * Сложная
     * Сложность O(n)
     * Доп.Память O(N)
     *
     */
    @NotNull
    @Override
    public SortedSet<T> tailSet(T fromElement) {
        if (tailSet == null) {
            tailSet = new MyLovelySet(null, fromElement);
        }
        if (fromElement.compareTo(tailSet.getFromElement()) != 0) {
            tailSet.setFromElement(fromElement);
        }
        tailSet.clear();
        for (T value : this) {
            if (value.compareTo(fromElement) >= 0) {
                tailSet.add(value);
            }
        }
        return tailSet;
    }

    public static void main(String[] args) {
        BinaryTree bt = new BinaryTree();
        for (int i = 0; i < 25; i++) {
            bt.add(i);
        }
    }


    @Override
    public T first() {
        if (root == null) throw new NoSuchElementException();
        Node<T> current = root;
        while (current.left != null) {
            current = current.left;
        }
        return current.value;
    }

    @Override
    public T last() {
        if (root == null) throw new NoSuchElementException();
        Node<T> current = root;
        while (current.right != null) {
            current = current.right;
        }
        return current.value;
    }
}
