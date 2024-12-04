package fileHandling;

import user.User;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class fileHandle {
    //File path to user text file
    public static final String UserFilePath = "./files/users.txt";
    //Method for reading user info
    public static List<User> readUsersFromFile()
    {
        List<User> users = new ArrayList<>();
        try
        {
            FileReader userFile = new FileReader(UserFilePath);
            BufferedReader br = new BufferedReader(userFile);
            String line = br.readLine();
            while (line != null)
            {
                String user_info[] = line.split(",");
                users.add(new User(user_info[0],user_info[1],user_info[2],user_info[3]));
                line = br.readLine();
            }
            br.close();
            userFile.close();
        }
        catch(IOException e)
        {
            System.out.println("Error loading users: " + e.getMessage());
        }
        return users;

    }
    //Method for writing user Info
    public static void writeUsersToFile(List<User> users)
    {
        try
        {
            FileWriter userFile = new FileWriter(UserFilePath);
            BufferedWriter bw = new BufferedWriter(userFile);
            for(User user : users)
            {
                bw.write(user.getUsername()+","+user.getEmail()+","+user.getPassword()+","+user.getDeliveryAddress());
                bw.newLine();
            }
            bw.close();
            userFile.close();
        }
        catch(IOException e)
        {
            System.out.println("Error writing users: " + e.getMessage());
        }
    }
}
