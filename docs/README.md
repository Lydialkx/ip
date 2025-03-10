# Lyxo User Guide

![Lyxo Screenshot](https://your-image-url.com)

##  **About Lyxo**

Lyxo is a simple and interactive chatbot that helps users manage tasks efficiently. It supports adding todos, deadlines, and events, as well as marking, unmarking, deleting, and finding tasks. Users can interact with Lyxo through a command-line interface to manage tasks.

---

## **Running Lyxo as a JAR File**

To run Lyxo, follow these steps:

1. Copy the `Lyxo.jar` file into an **empty folder**.
2. Open a **command window** in that folder.
3. Run the following command:
   ```sh
   java -jar Lyxo.jar
   ```
This will start the Lyxo chatbot.

---

## **Feature Overview**

This table provides a quick reference for all commands available.

| Feature           | Command Format                             |
|------------------|------------------------------------------|
| Add a Todo      | `todo <task description>`                 |
| Add a Deadline  | `deadline <task description> /by <date>`  | 
| Add an Event    | `event <task> /from <start> /to <end>`    |
| List Tasks      | `list`                                    | 
| Find Tasks      | `find <keyword>`                          |
| Mark Task as Done | `mark <task number>`                    |
| Unmark Task as Not Done | `unmark <task number>`            |
| Delete a Task   | `delete <task number>`                    |
| Exit Chatbot    | `bye`                                     |

---

## **Listing Tasks**

The `list` command shows all tasks stored in Lyxo.

### **Usage:**
```
list
```

### **Expected Output:**
```
Here are the tasks in your list:
1.[T][ ] CS assignment
2.[D][ ] return book (by: Jun 10 2025, 12:00 PM)
3.[E][ ] project meeting (from: Jun 15 2025, 2:00 PM to: Jun 15 2025, 4:00 PM)
```
---
## **Adding Tasks**

The `todo` command allows you to add a task.

### **Usage:**
```
todo <task>
```

### **Example:**
```
todo CS assignment
```

### **Expected Output:**
```
____________________________________________________________
Got it. I've added this task:
  [T][ ] CS assignment
Now you have 1 tasks in the list.
____________________________________________________________
```
---

## **Adding Deadlines**

The `deadline` command allows you to add a task with a due date or exact time.

### **Usage:**
```
deadline <task> by <yyyy-MM-dd>
deadline <task> by <yyyy-MM-dd HHmm>
```

### **Example:**
```
deadline return book /by 2025-06-10 1200
```

### **Expected Output:**
```
____________________________________________________________
Got it. I've added this task:
  [D][ ] return book (by: Jun 10 2025, 12:00 PM)
Now you have 2 tasks in the list.
____________________________________________________________
```
---
##  **Adding Events**

The `event` command adds a task with a start and end date or exact time.

### **Usage:**
```
event <task> /from <yyyy-MM-dd> /to <yyyy-MM-dd>
event <task> /from <yyyy-MM-dd HHmm> /to <yyyy-MM-dd HHmm>
```

### **Example:**
```
event project meeting /from 2025-06-15 1400 /to 2025-06-15 1600
```

### **Expected Output:**
```
____________________________________________________________
Got it. I've added this task:
  [E][ ] project meeting (from: Jun 15 2025, 2:00 PM to: Jun 15 2025, 4:00 PM)
Now you have 3 tasks in the list.
____________________________________________________________
```
---
## **Deleting Tasks**

The `delete` command removes a task by its index.

### **Usage:**
```
delete <task index>
```

### **Example:**
```
delete 3
```

### **Expected Output:**
```
____________________________________________________________
Noted. I've removed this task:
  [E][ ] project meeting (from: Jun 15 2025, 2:00 PM to: Jun 15 2025, 4:00 PM)
Now you have 2 tasks in the list.
____________________________________________________________
```
---

##  **Finding Tasks**

The `find` command searches for tasks containing a specific keyword.

### **Usage:**
```
find <keyword>
```

### **Example:**
```
find book
```

### **Expected Output:**
```
____________________________________________________________
Here are the matching tasks in your list:
1.[T][ ] read book
2.[D][ ] return book (by: Jun 10 2025)
____________________________________________________________
```
---
##  **Marking Task Completion Status**

The `mark` and `unmark` commands mark the completion status of a task.

### **Marking Tasks as Done**
#### **Usage:**
```
mark <task number>
```

#### **Example:**
```
mark 1
```

#### **Expected Output:**
```
____________________________________________________________
Nice! I've marked this task as done:
  [T][X] read book
____________________________________________________________
```

### **Unarking Tasks as Incomplete**
#### **Usage:**
```
unmark <task number>
```

#### **Example:**
```
unmark 2
```

#### **Expected Output:**
```
____________________________________________________________
OK, I've marked this task as not done yet:
  [D][ ] return book (by: Jun 10 2025, 12:00 PM)
____________________________________________________________
```
---

## **Exiting the Chatbot**

The `bye` command closes Lyxo.

### **Usage:**
```
bye
```

### **Expected Output:**
```
____________________________________________________________
Bye. Hope to see you again soon!
____________________________________________________________
```
---




 