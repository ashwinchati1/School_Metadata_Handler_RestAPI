package edmodo.school.meta.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import edmodo.school.meta.app.service.*;
import edmodo.school.meta.app.beans.*;

/**
 * Created by ashwin on 8/14/2017.
 */

// Meta App Controller..

@RestController
@ComponentScan(basePackageClasses = MetaAppService.class)
public class MetaAppController {

    @Autowired
    private MetaAppService metaService = new MetaAppService();

    /**
     * POST Request Handler..
     * @param data
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, value = "/school_metadata")
    public String insertData(@RequestBody Data data){
        return metaService.insertData(data);
    }

    /**
     * GET Request Handler..
     * @param id
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value ="/school_metadata/{id}")
    public Object getData(@PathVariable int id){
    	return metaService.getData(id);
    }

    /**
     * GET Request Handler..
     * @param id
     * @return
     */
   @RequestMapping(method = RequestMethod.GET, value ="/school_metadata")
   @ResponseBody
    public Object getAllData(@RequestParam("id") List<Integer> id){
        return metaService.getAllData(id);
    }

    /**
     * PUT Request Handler..
     * @param id
     * @return
     */
    @RequestMapping(method = RequestMethod.PUT, value ="/school_metadata/{id}")
    public String updateData(@RequestBody Data data, @PathVariable int id){
        return metaService.updateData(data, id);
    }

    /**
     * DELETE Request Handler..
     * @param id
     * @return
     */
    @RequestMapping(method = RequestMethod.DELETE, value ="/school_metadata/{id}")
    public String deleteData(@PathVariable int id){
        return metaService.deleteData(id);
    }

}
