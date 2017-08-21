package edmodo.school.meta.app.SchoolMetadataApp;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.boot.SpringApplication;
import edmodo.school.meta.app.controller.*;
/**
 * Created by ashwin on 8/14/2017.
 *
 */

//MetaData App eEntry point..

@SpringBootApplication
@ComponentScan(basePackageClasses = MetaAppController.class)
public class App 
{
	 //Main Class..
    public static void main(String[] args){
        SpringApplication.run(App.class,args);
    }
}
