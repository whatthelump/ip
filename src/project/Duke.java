package project;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) throws StringIndexOutOfBoundsException {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Greetings and salutations! I am Duke, a helpful chatbot.\nPlease enter your command(s).");
        System.out.println("Available commands: todo (task), deadline (task) /by (time), event (task) /at (time)");
        System.out.println("To view the list of tasks, enter the command 'list'.");
        System.out.println("To exit, enter the command 'bye'.");
        Scanner sc = new Scanner(System.in);
        List<Task> list = new ArrayList<>(100);
        while (true) {
            String input = sc.nextLine();
            try {
                if (input.equals("bye")) {
                    break;
                } else if (input.equals("list")) {
                    if (list.isEmpty()) {
                        System.out.println("The list of tasks is empty!");
                    } else {
                        StringBuilder output = new StringBuilder();
                        for (int i = 1; i <= list.size(); i++) {
                            output.append(i).append(". ").append(list.get(i - 1)).append("\n");
                        }
                        System.out.println(output);
                    }
                } else if (input.substring(0, 4).equals("done")) {
                    try {
                        int pointer = Integer.parseInt(input.substring(5)) - 1;
                        Task task = list.get(pointer);
                        task.markAsDone();
                        list.set(pointer, task);
                        System.out.println("Sweet! I've marked this task as done:\n" + list.get(pointer));
                    } catch (NumberFormatException e) {
                        System.out.println("Number format exception, enter a number from 1-100!");
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("Array index is out of bounds, the current number of elements in the list " +
                                "is " + list.size() + ".");
                    }
                } else if (input.substring(0, 4).equals("todo")) {
                    try {
                        todo t = new todo(input.substring(5));
                        list.add(t);
                        System.out.println("Got it. I've added this task:\n" +
                                t.toString() + "\nNow you have " + list.size() + " tasks in the list.");
                    } catch (StringIndexOutOfBoundsException e){
                        System.out.println("☹ OOPS!!! The description of a " + input + " cannot be empty.");
                    }
                } else if (input.substring(0, 5).equals("event")) {
                    try {
                        String[] split = input.split("/");
                        String description = split[0].substring(6);
                        String at = split[1].substring(3);
                        Event e = new Event(description, at);
                        list.add(e);
                        System.out.println("Got it. I've added this task:\n" +
                                e.toString() + "\nNow you have " + list.size() + " tasks in the list.");
                    } catch (StringIndexOutOfBoundsException e){
                        System.out.println("☹ OOPS!!! The description of a " + input + " cannot be empty.");
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println("☹ OOPS!!! The format for an event task should be 'event (task)" +
                                " /at (time)'");
                    }
                } else if (input.substring(0, 6).equals("delete")) {
                    try {
                        int pointer = Integer.parseInt(input.substring(7)) - 1;
                        Task task = list.get(pointer);
                        list.remove(pointer);
                        System.out.println("Noted. I've removed this task:\n" + task.toString());
                    } catch (NumberFormatException e) {
                        System.out.println("Number format exception, enter a number from 1-100!");
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("Array index is out of bounds, the current number of elements in the list " +
                                "is " + list.size() + ".");
                    }
                } else if (input.substring(0, 8).equals("deadline")) {
                    try {
                        String[] split = input.split("/");
                        String description = split[0].substring(9);
                        String by = split[1].substring(3);
                        Deadline d = new Deadline(description, by);
                        list.add(d);
                        System.out.println("Got it. I've added this task:\n" +
                                d.toString() + "\nNow you have " + list.size() + " tasks in the list.");
                    } catch (StringIndexOutOfBoundsException e){
                        System.out.println("☹ OOPS!!! The description of a " + input + " cannot be empty.");
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println("☹ OOPS!!! The format for a deadline task should be 'deadline (task)" +
                                " /by (time)'");
                    }
                } else {
                    System.out.println("☹ OOPS!!! I'm sorry, but I don't know what '" + input + "' means :-(");
                }
            } catch (StringIndexOutOfBoundsException e) {
                System.out.println("☹ OOPS!!! I'm sorry, but I don't know what '" + input + "' means :-(");
            }
        }
        System.out.println("Farewell. Hope that you will visit again soon!");

    }
}
