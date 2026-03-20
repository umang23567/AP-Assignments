public class Feedback<T> 
{
    private Course course;
    private T feedback;

    public Feedback(Course course, T feedback) 
    {
        this.course = course;
        this.feedback = feedback;
    }

    public Course getCourse() 
    {
        return course;
    }

    public T getFeedback() 
    {
        return feedback;
    }
}
