public class FixedStack<T>
{
    private T[] stack;
    private int size;
    private int top;

    public FixedStack(int size)
    {
        this.stack = (T[]) new Object[size];
        this.top = -1;
        this.size = size;
    }

    public void push(T obj)
    {
        if (top >= size) throw new StackOverflowError("Size = " + size);
        stack[++top] = obj;
    }

    public T pop()
    {
        if (top < 0) throw new IndexOutOfBoundsException();
        T obj = stack[top--];
        stack[top + 1] = null;
        return obj;
    }

    public int size()
    {
        return size;
    }

    public int elements()
    {
        return top + 1;
    }
}