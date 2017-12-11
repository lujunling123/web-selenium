package webtest.core.util;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;

import webtest.core.TestCase;
import webtest.core.TestCase.DriverManager;

public class PageTool {

	/**
	 * 重新载入页面同名元素集合
	 */
	public List<WebElement> reLoadByClass(String className){
		return DriverManager.getDriver().findElements(By.className(className));
	}
	public List<WebElement> reLoadByTag(String tagName){
		return DriverManager.getDriver().findElements(By.tagName(tagName));
	}
	public List<WebElement> reLoadByName(String name){
		return DriverManager.getDriver().findElements(By.name(name));
	}

	/**
	 * 切换页签
	 * @return
	 */
	public String changeHandel() {
		String thisHandel = DriverManager.getDriver().getWindowHandle();
		Set<String> handels = DriverManager.getDriver().getWindowHandles();
		for (String s : handels) {
			if (!s.equals(thisHandel)) {
				DriverManager.getDriver().switchTo().window(s);
			}
		}
		return thisHandel;
	}

	public void changeHandel(String handel) {
		DriverManager.getDriver().switchTo().window(handel);
	}

	public void snapshot(String fileName) {
		File screenshot = ((TakesScreenshot)DriverManager.getDriver()).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(screenshot, new File("surefire-reports/image/" +fileName+".jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
