<%@ include file="/WEB-INF/jsp/header.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
  <div id="container">
  	<form:form method="get" action="/hr/searchEmployee.htm">
	  	<input type="submit" id="Button2" value="Резюме" style="background-color:#fff;color:#3370B7;position:absolute;right:134px;top:120px;border-top-left-radius:4px;border-top-right-radius:0px;border-bottom-left-radius:4px;border-bottom-right-radius:0px;width:96px;height:36px;font-size:17px;z-index:52;">
	  	<input type="submit" id="Button3" disabled value="Вакансии" style="position:absolute;right:38px;top:120px;border-top-left-radius:0px;border-top-right-radius:4px;border-bottom-left-radius:0px;border-bottom-right-radius:4px;width:96px;height:36px;font-size:17px;z-index:52;">
    </form:form>
    <div style="position:absolute;left:14px;top:120px;width:1148px">
  	<form:form modelAttribute="sc" method="post" style="width:1148px">
  		<br>
  		Должность<br>
    	<form:input path="jobHunt.job.name" id="Editbox1" style="margin-top:6px;margin-bottom:15px;width:99%;height:27px;line-height:26px;font-size:14px;z-index:2;" value="" spellcheck="true"/>
  		<br>
  		<table style="margin-bottom:6px">
  		<tr>
  			<td style="width:50%">Образование</td>
  			<td>Зарплата от</td>
  		</tr>
	  		<td><form:select items="${educations}" path="jobHunt.employee.education.id" name="Combobox1" size="1" id="Combobox1" style="margin-right:15px;width:97%;height:37px;z-index:2;"/></td>
	  		<td><form:input path="jobHunt.salaryMin" id="Editbox3" style="width:98%;height:27px;line-height:26px;font-size:14px;z-index:2;" value="" spellcheck="true"/></td>
  		</table>
  		<br>
  		<b>Компании</b><br>
  		<form:select path="companiesID" size="8" multiple="true" style="width:100%;z-index:6;font-size:17px">
	      <c:forEach items="${companies}" var="com">
	         <form:option value="${com.id}">${com.name}</form:option>
	      </c:forEach> 
	      </form:select> 
  		<p align="right">
	  		<input type="submit" name="clear" value="Сбросить" style="width:120px;height:50px;font-size:19px;z-index:53;margin-right:15px">
  		<input type="submit" name="search" value="Найти" style="width:120px;height:50px;font-size:19px;z-index:13"></p>
  	</form:form> 
  	
  	<h1 id="Heading1">Вакансии</h1><br>
  	<table border=1 cellpadding=5 style="margin-top:5px;text-allign:middle">
	      	<tr>
	      		<td><b>Компания</b></td>
	      		<td><b>Должность</b></td>
	      		<td><b>Зарплата</b></td>
	      	</tr>
	      	<c:forEach items="${vacs}" var="vac">
	      	<tr>
	      		<td><a href="/hr/com.profile.htm?companyID=${vac.company.id}">${vac.company.name}</a></td>
	      		<td>${vac.job.name}</td>
	      		<td>${vac.salary} ₽</td>
	      	</tr>
	      	</c:forEach>
	      </table><br><br><br><br>
	 </div>
  </div>
  
<%@ include file="/WEB-INF/jsp/footer.jsp" %>