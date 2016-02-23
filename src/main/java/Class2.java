import java.util.List;
import org.sql2o.*;


  @Override
  public boolean equals(Object otherTask){
    if (!(otherTask instanceof Task)) {
      return false;
    } else {
      Task newTask = (Task) otherTask;
      return this.getDescription().equals(newTask.getDescription()) && this.getDueDate() == newTask.getDueDate() && this.getId() == newTask.getId();
    }
  }
