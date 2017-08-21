package edmodo.school.meta.app.service;


import edmodo.schoo.meta.app.dao.MetadataDAO;
import edmodo.school.meta.app.beans.Data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ashwin on 8/14/2017.
 */

@Service
@ComponentScan(basePackageClasses = MetadataDAO.class)
public class MetaAppService{

    @Autowired
    private MetadataDAO meta;

    /**
     * Service function to insert data..
     * @param data
     * @return
     */
    public String insertData(Data data){
        return meta.insertIntoTables(data.getExternal_school(),data.getExternal_school_rating(), data.getExternal_school_subject_stats());
    }

    /**
     * Service function to fetch all data matches specified Query criteria..
     * @param edmodoId
     * @return
     */
    public Object getAllData(List<Integer> edmodoId){
        List<Data> allResult = new ArrayList<Data>();
        for(int id : edmodoId){
            allResult.add((Data) meta.getDataFromTables(id, "getAll"));
        }
        return allResult;
    }

    /**
     * Service function to get data for specified id..
     * @param id
     * @return
     */
    public Object getData(int id){
        return meta.getDataFromTables(id, "getSpecific");
    }

    /**
     * Service function to update the data..
     * @param data
     * @param id
     * @return
     */
   public String updateData(Data data, int id){
        return meta.updateDataInTables(data.getExternal_school(), data.getExternal_school_rating(), data.getExternal_school_subject_stats(), id);
    }

    /**
     * Service function to delete the data..
     * @param id
     * @return
     */
    public String deleteData(int id){
        return meta.deleteDataFromTables(id);
    }

}