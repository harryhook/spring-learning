package com.coderunning;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

/**
 * @author haowei.chen
 * @since 2024/3/13 23:47
 */
class Base62UtilsTest {

    @Test
    public void testEncode() {
        Assert.assertEquals("1", Base62Utils.encode(1L));
        Assert.assertEquals("a", Base62Utils.encode(10L));
        Assert.assertEquals("0", Base62Utils.encode(0L));
        Assert.assertEquals("A", Base62Utils.encode(36L));
        Assert.assertEquals("10", Base62Utils.encode(62L));
        Assert.assertEquals("10", Base62Utils.encode(62L));
        Assert.assertEquals("1C", Base62Utils.encode(100L));

    }

    @Test
    public void testDecode() {
        Assert.assertEquals(1L, Base62Utils.decode("1"));
        Assert.assertEquals(10L, Base62Utils.decode("a"));
        Assert.assertEquals(36L, Base62Utils.decode("A"));
        Assert.assertEquals(62L, Base62Utils.decode("10"));

    }

}

//Generated with love by TestMe :) Please raise issues & feature requests at: https://weirddev.com/forum#!/testme