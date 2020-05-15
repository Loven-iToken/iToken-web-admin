import com.loven.iToken.web.admin.WebAdminApplication;
import com.loven.iToken.web.admin.service.AdminService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = WebAdminApplication.class)
@ActiveProfiles(profiles = "dev")
@Rollback
public class UserControllerTest {
    @Autowired
    AdminService adminService;

    @Test
    public void register(){
        Map<String, Object> params = new HashMap<>();
        params.put("userCode", UUID.randomUUID().toString());
        params.put("loginCode", "loven@gmail.com");
        params.put("userName", "loven");
        params.put("password", "123456");
        params.put("userType", "1");
        params.put("mgrType", "2");
        params.put("status", "0");
        params.put("createBy", "loven");
        params.put("createDate", new Date());
        params.put("updateBy", "loven");
        params.put("updateDate", new Date());
        params.put("corpCode", "0");
        params.put("corpName", "iToken");
        System.out.println(params);
    }
}
