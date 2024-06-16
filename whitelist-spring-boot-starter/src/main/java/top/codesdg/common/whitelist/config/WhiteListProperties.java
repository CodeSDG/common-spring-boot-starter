package top.codesdg.common.whitelist.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * @author shandeguo
 * @version V1.0
 * @date 2024/6/15 下午9:40
 * @description
 * @Copyright
 */
@ConfigurationProperties("codesdg.whitelist")
public class WhiteListProperties {
    /**
     * 白名单
     */
    private List<String> users;

    private boolean enabled = false;

    public List<String> getUsers() {
        return users;
    }

    public void setUsers(List<String> users) {
        this.users = users;
    }


    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
