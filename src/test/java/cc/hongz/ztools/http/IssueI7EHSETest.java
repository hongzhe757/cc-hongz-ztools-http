/*
 * Copyright (c) 2023 looly(loolly@aliyun.com)
 * Hutool is licensed under Mulan PSL v2.
 * You can use this software according to the terms and conditions of the Mulan PSL v2.
 * You may obtain a copy of Mulan PSL v2 at:
 *          http://license.coscl.org.cn/MulanPSL2
 * THIS SOFTWARE IS PROVIDED ON AN "AS IS" BASIS, WITHOUT WARRANTIES OF ANY KIND,
 * EITHER EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO NON-INFRINGEMENT,
 * MERCHANTABILITY OR FIT FOR A PARTICULAR PURPOSE.
 * See the Mulan PSL v2 for more details.
 */

package cc.hongz.ztools.http;

import cc.hongz.ztools.core.lang.Console;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Issue I7EHSE 请求路径编码测试")
public class IssueI7EHSETest {
    @Test
    @Disabled
    @DisplayName("请求路径编码测试")
    public void encodePathTest() {
        String body = HttpUtil
            .createGet("http://hq.sinajs.cn/list=s_sh600090")
            .header("Referer", "http://finance.sina.com.cn")
            .execute()
            .body();

        Console.log(body);
    }
}
