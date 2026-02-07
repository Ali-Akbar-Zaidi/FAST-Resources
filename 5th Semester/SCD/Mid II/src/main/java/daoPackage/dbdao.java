package daoPackage;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import java.sql.*;

public class dbdao implements idao {

    @Override
    public List<Map<String, String>> loadAll() {

        List<Map<String, String>> list = new ArrayList<>();
            try(Connection conn = DBConnection.getConnection()){
            String sql="Select * from imageGallery";
            PreparedStatement stmt= conn.prepareStatement(sql);
            ResultSet rs=stmt.executeQuery();
            while(rs.next()){
                Map<String,String> map=new HashMap<>();
                map.put("id",String.valueOf(rs.getInt("id")));
                map.put("imagePath", rs.getString("imagepath"));
                map.put("description", rs.getString("description"));
                list.add(map);
            }
            return list;
         
        }
        catch(Exception ex){
        }

        return list;
    }
}
