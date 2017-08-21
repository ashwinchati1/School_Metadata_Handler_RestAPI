package edmodo.schoo.meta.app.dao;

import org.springframework.stereotype.Component;

import edmodo.school.meta.app.beans.*;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.annotation.Resource;

/**
 * Created by ashwin on 8/14/2017.
 */

/**
 * Data Access Object (DAO) Layer..
 */
@Component
public class MetadataDAO {

    /**
     * Connection and SQL Statements..
     */
    private static Connection connection;
    private final static String createExtSchTable = "CREATE TABLE IF NOT EXISTS external_school (id integer NOT NULL,edmodo_school_id integer NOT NULL,url text,name text ,grades text,student_count integer,type integer,nces_id integer,CONSTRAINT external_school_pkey PRIMARY KEY (id))";
    private final static String createExtSchRatTable = "CREATE TABLE IF NOT EXISTS external_school_rating (id integer NOT NULL,external_school_id integer,five integer,four integer,three integer,two integer,one numeric,average real,num_ratings integer,CONSTRAINT external_school_rating_pkey PRIMARY KEY (id),CONSTRAINT ext_sch_fk FOREIGN KEY (external_school_id) REFERENCES external_school (id) MATCH SIMPLE ON UPDATE CASCADE ON DELETE CASCADE)";
    private final static String createSubStatsTable = "CREATE TABLE IF NOT EXISTS external_school_subject_stats (id integer NOT NULL,external_school_id integer,edmodo_subject_id integer NOT NULL,avg real,state_avg real,CONSTRAINT \"EXTERNAL_SCHOOL_SUBJECT_STATS_pkey\" PRIMARY KEY (id),CONSTRAINT ext_sch_id_fk FOREIGN KEY (external_school_id) REFERENCES external_school (id) MATCH SIMPLE ON UPDATE CASCADE ON DELETE CASCADE)";
    private final static String extSchTabInsert = "INSERT INTO EXTERNAL_SCHOOL (ID,EDMODO_SCHOOL_ID,URL,NAME,GRADES,STUDENT_COUNT,TYPE,NCES_ID) VALUES (?,?,?,?,?,?,?,?)";
    private final static String extSchRtngTabInsert = "INSERT INTO EXTERNAL_SCHOOL_RATING VALUES (?,?,?,?,?,?,?,?,?)";
    private final static String extSchSubStatTabInsert = "INSERT INTO EXTERNAL_SCHOOL_SUBJECT_STATS VALUES (?,?,?,?,?)";
    private final static String getDataExtSchTable = "SELECT id, edmodo_school_id, url, name, grades, student_count, type, nces_id from external_school where id = ?";
    private final static String getDataExtSchRatTable = "SELECT id, external_school_id, five, four, three, two, one, average, num_ratings from external_school_rating where external_school_id = ?";
    private final static String getDataSubStatTable = "SELECT id, external_school_id, edmodo_subject_id, avg, state_avg from external_school_subject_stats where external_school_id = ?";
    private final static String getExtSchId = "SELECT id, edmodo_school_id, url, name, grades, student_count, type, nces_id from external_school where edmodo_school_id = ?";
    private final static String updateExtSchTable = "UPDATE EXTERNAL_SCHOOL set EDMODO_SCHOOL_ID = ? ,URL = ?,NAME = ? ,GRADES = ?,STUDENT_COUNT = ?,TYPE = ?,NCES_ID = ? where id = ?";
    private final static String updateExtSchRatTable = "UPDATE EXTERNAL_SCHOOL_RATING SET five = ?, four = ?, three = ?, two = ?, one = ?, average = ?, num_ratings = ? WHERE external_school_id = ?";
    private final static String updateSubStaTable = "UPDATE EXTERNAL_SCHOOL_SUBJECT_STATS SET edmodo_subject_id = ?, avg = ?, state_avg = ? WHERE external_school_id = ? AND id = ?";
    private final static String deleteFromExtEchTable = "DELETE FROM EXTERNAL_SCHOOL WHERE ID = ?";
    private final static String deleteFromExtSchRatTable ="DELETE FROM EXTERNAL_SCHOOL_RATING WHERE external_school_id = ?";
    private final static String deleteFromSubStatTable = "DELETE FROM external_school_subject_stats WHERE external_school_id = ?";


    /**
     * Initialize the connection..
     * @return
     */
    public Connection initializeConnection(){
    	
    	Properties prop = new Properties();
    	
    	InputStream input = null;
    	
        try{
        	//Read parameters from config.properties file..
        	
        	
        	input = new FileInputStream("./src/main/resources/config.properties");
        	prop.load(input);
        	String url = System.getenv("JDBC_DATABASE_URL");
        	String user = System.getenv("JDBC_DATABASE_USERNAME");
        	String password = System.getenv("JDBC_DATABASE_PASSWORD");
        	
        	//Make connection to database.. 
            if(connection == null){
                Class.forName("org.postgresql.Driver").newInstance();
                DriverManager.registerDriver(new org.postgresql.Driver());
                //connection = DriverManager.getConnection("jdbc:postgresql://localhost:5433/postgres","postgres","password");
                connection = DriverManager.getConnection(url,user,password);
            }
            if (connection != null) {
                System.out.println("Connected to the database");
            }
        }catch (ClassNotFoundException ex) {
            System.out.println("Could not find database driver class");
            ex.printStackTrace();
        } catch (SQLException ex) {
            System.out.println("An error occurred. Maybe user/password is invalid");
            ex.printStackTrace();
        }catch(Exception e){

        }
        return connection;
    }

    public void createSchema(){
    	initializeConnection();
    	PreparedStatement preparedStatement = null;
    	try{
    	preparedStatement = connection.prepareStatement(createExtSchTable);
    	preparedStatement.execute();
    	preparedStatement = connection.prepareStatement(createExtSchRatTable);
    	preparedStatement.execute();
    	preparedStatement = connection.prepareStatement(createSubStatsTable);
    	preparedStatement.execute();
    	preparedStatement.close();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    	
    }
    /**
     * Insert the data into the database tables..
     * @param sData
     * @param sRating
     * @param subStats
     * @return
     */
    public String insertIntoTables(SchoolData sData, SchoolRating sRating, List<SubjectStats> subStats) {
    	createSchema();
    	if(insertTable(sData) > 0 && insertTable(sRating) > 0 && insertTable(subStats) > 0){
            return "Insertion Successful";
        }else{
            return "Insert Unsuccessful.";
        }
    }
    
    /**
     * Insert Data into External_School table..
     * @param sd
     * @return
     */
    public int insertTable(SchoolData sd){
        initializeConnection();
        PreparedStatement preparedStatement = null;
        int status = 0;
        try{
            preparedStatement = connection.prepareStatement(extSchTabInsert);
            preparedStatement.setInt(1,sd.getId());
            preparedStatement.setInt(2,sd.getEdmodo_school_id());
            preparedStatement.setString(3,sd.getExternal_school_url());
            preparedStatement.setString(4,sd.getName());
            preparedStatement.setString(5,sd.getGrades());
            preparedStatement.setInt(6,sd.getStudent_count());
            preparedStatement.setInt(7,sd.getSchool_type());
            preparedStatement.setLong(8,sd.getNces_id());
            status = preparedStatement.executeUpdate();
            preparedStatement.close();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return status;
    }
    
    /**
     * Insert data into External_School_Rating table..
     * @param sr
     * @return
     */
    public int insertTable(SchoolRating sr){
        initializeConnection();
        PreparedStatement preparedStatement = null;
        int status = 0;
        try{
            preparedStatement = connection.prepareStatement(extSchRtngTabInsert);
            preparedStatement.setInt(1,sr.getId());
            preparedStatement.setInt(2,sr.getExternal_school_id());
            preparedStatement.setInt(3,sr.getFive());
            preparedStatement.setInt(4,sr.getFour());
            preparedStatement.setInt(5,sr.getThree());
            preparedStatement.setInt(6,sr.getTwo());
            preparedStatement.setInt(7,sr.getOne());
            preparedStatement.setFloat(8,sr.getAverage());
            preparedStatement.setInt(9,sr.getNum_ratings());
            status = preparedStatement.executeUpdate();
            preparedStatement.close();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return status;
    }
    
    /**
     * Insert data into External_School_Subject_Stats table..
     * @param st
     * @return
     */
    public int insertTable(List<SubjectStats> st){
        initializeConnection();
        PreparedStatement preparedStatement = null;
        int status = 0;
        try{
            for(SubjectStats subject : st){
                preparedStatement = connection.prepareStatement(extSchSubStatTabInsert);
                preparedStatement.setInt(1,subject.getId());
                preparedStatement.setInt(2,subject.getExternal_school_id());
                preparedStatement.setInt(3,subject.getEdmodo_subject_id());
                preparedStatement.setFloat(4,subject.getAvg());
                preparedStatement.setFloat(5,subject.getState_avg());
                status = preparedStatement.executeUpdate();
                preparedStatement.close();
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return status;
    }
    
    /**
     * Delete Data in the database..
     * @param id
     * @return
     */
    public String deleteDataFromTables(int id){
        if(deleteFromTable(id, deleteFromSubStatTable) > 0 && deleteFromTable(id, deleteFromExtSchRatTable) > 0 && deleteFromTable(id, deleteFromExtEchTable) > 0){
            return "Record Deleted.";
        }else{
            return "Record does not exist.";
        }
    }
    
    /**
     * Delete data in External_School_Rating table..
     * @param id
     * @return
     */
    public int deleteFromTable(int id, String query){
    	
        initializeConnection();
        PreparedStatement preparedStatement = null;
        int status = 0;
        try{
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,id);
            status = preparedStatement.executeUpdate();
            preparedStatement.close();
        }catch(Exception e){
            e.getMessage();
        }
        return status;
    }
    
    public String updateDataInTables(SchoolData sData, SchoolRating sRating, List<SubjectStats> subjectStats, int id){
        int s1 = 0, s2 = 0, s3 = 0;
        if(sData != null){
            s1 = updateTable(sData, id);
        }
        if(sRating != null){
            s2 = updateTable(sRating, id);
        }
        if(subjectStats != null){
            s3 = updateTable(subjectStats, id);
        }
        if(s1 > 0 || s2 > 0 || s3 > 0){
            return "Updated Successful.";
        }else{
            return "No records updated.";
        }
    }
    
    /**
     * Update data in External_School table..
     * @param sData
     * @param id
     * @return
     */
    public int updateTable(SchoolData sData, int id){
        initializeConnection();
        PreparedStatement preparedStatement = null;
        int status = 0;
        try{
            preparedStatement = connection.prepareStatement(updateExtSchTable);
            preparedStatement.setInt(1,sData.getEdmodo_school_id());
            preparedStatement.setString(2,sData.getExternal_school_url());
            preparedStatement.setString(3,sData.getName());
            preparedStatement.setString(4,sData.getGrades());
            preparedStatement.setInt(5,sData.getStudent_count());
            preparedStatement.setInt(6,sData.getSchool_type());
            preparedStatement.setLong(7,sData.getNces_id());
            preparedStatement.setInt(8,id);
            status = preparedStatement.executeUpdate();
            preparedStatement.close();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return status;
    }
    
    /**
     * Update data in External_School_Rating table..
     * @param rData
     * @param id
     * @return
     */
    public int updateTable(SchoolRating rData, int id){
        initializeConnection();
        PreparedStatement preparedStatement = null;
        int status = 0;
        try{
            preparedStatement = connection.prepareStatement(updateExtSchRatTable);
            preparedStatement.setInt(1,rData.getFive());
            preparedStatement.setInt(2,rData.getFour());
            preparedStatement.setInt(3,rData.getThree());
            preparedStatement.setInt(4,rData.getTwo());
            preparedStatement.setInt(5,rData.getOne());
            preparedStatement.setFloat(6,rData.getAverage());
            preparedStatement.setInt(7,rData.getNum_ratings());
            preparedStatement.setInt(8,id);
            status = preparedStatement.executeUpdate();
            preparedStatement.close();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return status;
    }
    
    /**
     * Update data in External_School_Subject_Stats table..
     * @param st
     * @param id
     * @return
     */
    public int updateTable(List<SubjectStats> st, int id){
        initializeConnection();
        PreparedStatement preparedStatement = null;
        int status = 0;
        try{
            for(SubjectStats subject : st){
                preparedStatement = connection.prepareStatement(updateSubStaTable);
                preparedStatement.setInt(1,subject.getEdmodo_subject_id());
                preparedStatement.setFloat(2,subject.getAvg());
                preparedStatement.setFloat(3,subject.getState_avg());
                preparedStatement.setInt(4,id);
                preparedStatement.setInt(5,subject.getId());
                status = preparedStatement.executeUpdate();
                preparedStatement.close();
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return status;
    }
    
    public Object getDataFromTables(int id, String request){
        Data result = new Data();
        SchoolData sData = null;
        SchoolRating sRating = null;
        List<SubjectStats> stats = null;
        if(request.equals("getAll")){
        	sData = getData(id, request);
            int extId = result.getExternal_school().getId();
            sRating = getDataExternalSchoolRatingTable(extId);
            stats = getDataSubStatsTable(extId);
            
            if(sData != null && sRating != null && stats != null) {
            	setResult(result,sData,sRating,stats);
            	return result;
            }else {
            	return "Record does not exists";
            }
            
        }else if(request.equals("getSpecific")){
            sData = getData(id, request);
            sRating = getDataExternalSchoolRatingTable(id);
            stats = getDataSubStatsTable(id);
            
            if(sData != null && sRating != null && stats != null) {
            	setResult(result,sData,sRating,stats);
            	return result;
            }else {
            	return "Record does not exists";
            }
        }
        return "Record does not exists";
    }
    
    public void setResult(Data result, SchoolData sData, SchoolRating sRating, List<SubjectStats> stats) {
    	result.setExternal_school(sData);
    	result.setExternal_school_rating(sRating);
    	result.setExternal_school_subject_stats(stats);
    }
    
    /**
     * Get data from External_School table..
     * @param id
     * @param request
     * @return
     */
    public SchoolData getData(int id, String request){
        initializeConnection();
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        SchoolData sData = null;
        try{
            if(request.equals("getSpecific")){
                preparedStatement = connection.prepareStatement(getDataExtSchTable);
            }else if(request.equals("getAll")){
                preparedStatement = connection.prepareStatement(getExtSchId);
            }

            preparedStatement.setInt(1,id);
            rs = preparedStatement.executeQuery();
            if(rs.next()){
            	sData = new SchoolData();
                sData.setId(rs.getInt("id"));
                sData.setEdmodo_school_id(rs.getInt("edmodo_school_id"));
                sData.setExternal_school_url(rs.getString("url"));
                sData.setName(rs.getString("name"));
                sData.setGrades(rs.getString("grades"));
                sData.setStudent_count(rs.getInt("student_count"));
                sData.setSchool_type(rs.getInt("type"));
                sData.setNces_id(rs.getInt("nces_id"));
            }
            preparedStatement.close();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return sData;
    }
    
    /**
     * Get data from External_School_Rating table..
     * @param id
     * @return
     */
    public SchoolRating getDataExternalSchoolRatingTable(int id){
        initializeConnection();
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        SchoolRating sRating = null;
        try{
            preparedStatement = connection.prepareStatement(getDataExtSchRatTable);
            preparedStatement.setInt(1,id);
            rs = preparedStatement.executeQuery();
            if(rs.next()){
            	sRating = new SchoolRating();
                sRating.setId(rs.getInt("id"));
                sRating.setExternal_school_id(rs.getInt("external_school_id"));
                sRating.setFive(rs.getInt("five"));
                sRating.setFour(rs.getInt("four"));
                sRating.setThree(rs.getInt("three"));
                sRating.setTwo(rs.getInt("two"));
                sRating.setOne(rs.getInt("one"));
                sRating.setAverage(rs.getFloat("average"));
                sRating.setNum_ratings(rs.getInt("num_ratings"));
            }
            preparedStatement.close();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return sRating;
    }
    
    /**
     * Get data from External_School_Subject_Stats table..
     * @param id
     * @return
     */
    public List<SubjectStats> getDataSubStatsTable(int id){
        initializeConnection();
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        List<SubjectStats> list = new ArrayList<SubjectStats>();
        try{
            preparedStatement = connection.prepareStatement(getDataSubStatTable);
            preparedStatement.setInt(1,id);
            rs = preparedStatement.executeQuery();
            while(rs.next()){
                    SubjectStats stats = new SubjectStats();
                    stats.setId(rs.getInt("id"));
                    stats.setExternal_school_id(rs.getInt("external_school_id"));
                    stats.setEdmodo_subject_id(rs.getInt("edmodo_subject_id"));
                    stats.setAvg(rs.getFloat("avg"));
                    stats.setState_avg(rs.getFloat("state_avg"));
                    list.add(stats);
            }
            preparedStatement.close();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return list;
    }
}
