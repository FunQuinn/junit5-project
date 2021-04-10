package com.unit_suite;

import com.unit_case.AnnotationTest;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.IncludeTags;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.runner.RunWith;

/**
 * @des: 选择包名进行执行对应的数据
*/
@RunWith(JUnitPlatform.class)
//@SelectPackages({"com.junit5testdemo1","com.junit5testdemo2"})
//@IncludePackages("com.junit5testdemo2")
//@ExcludePackages("com.junit5testdemo2")


/**
 * @des: 选择类下含有Tag的标签进行执行
 */
@SelectClasses({AnnotationTest.class})
@IncludeTags("testdemo")
public class SuiteTest {

}

