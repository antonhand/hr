<%@ include file="/WEB-INF/jsp/header.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
  <div id="container">
  	<form:form method="get" action="/hr/searchCompany.htm">
	  	<input type="submit" id="Button2" disabled value="Резюме" style="position:absolute;right:134px;top:120px;border-top-left-radius:4px;border-top-right-radius:0px;border-bottom-left-radius:4px;border-bottom-right-radius:0px;width:96px;height:36px;font-size:17px;z-index:52;">
	  	<input type="submit" id="Button3" value="Вакансии" style="position:absolute;right:38px;top:120px;background-color:#fff;color:#3370B7;border-top-left-radius:0px;border-top-right-radius:4px;border-bottom-left-radius:0px;border-bottom-right-radius:4px;width:96px;height:36px;font-size:17px;z-index:52;">
    </form:form>
    <div style="position:absolute;left:14px;top:120px;width:1148px">
  	<form:form modelAttribute="vac" method="post" style="width:1148px">
  		<br>
  		Должность<br>
    	<form:input path="job.name" id="Editbox1" style="margin-top:6px;margin-bottom:15px;width:99%;height:27px;line-height:26px;font-size:14px;z-index:2;" value="" spellcheck="true"/>
  		<br>
  		<table style="margin-bottom:6px">
  		<tr>
  			<td style="width:50%">Образование</td>
  			<td>Зарплата до</td>
  		</tr>
	  		<td><form:select items="${educations}" path="education.id" name="Combobox1" size="1" id="Combobox1" style="margin-right:15px;width:97%;height:37px;z-index:2;"/></td>
	  		<td><form:input path="salary" id="Editbox3" style="width:98%;height:27px;line-height:26px;font-size:14px;z-index:2;" value="" spellcheck="true"/></td>
  		</table>
  		<br>
  		<b>Опыт работы</b><br>
  		<table style="margin-top:6px">
  		<tr>
  			<td style="padding-bottom:6px">Должность</td>
  			<td style="padding-bottom:6px">Организация</td>
  			<td style="padding-bottom:6px">Стаж (месяцев)</td>
  			<td style="width:26px"></td>
  		</tr>
  		<c:forEach items="${vac.vacancyReqs}" var="vr" varStatus="status">
  		<tr>
  			<td><input type="text" name="vacancyReqs[${status.index}].job.name" id="Editbox4" style="width:95%;height:27px;line-height:26px;font-size:14px;z-index:2;" value="${vr.job.name}" spellcheck="true"/></td>
  			<td><input type="text" name="vacancyReqs[${status.index}].company.name" id="Editbox5" style="width:95%;height:27px;line-height:26px;font-size:14px;z-index:2;" value="${vr.company.name}" spellcheck="true"/></td>
  			<td><input type="text" name="vacancyReqs[${status.index}].expMonths" id="Editbox6" style="width:95%;height:27px;line-height:26px;font-size:14px;z-index:2;" value="${vr.expMonths}" spellcheck="true"/></td>
  			<td>
  			<c:if test="${status.last}">
  				<input type="submit" name="add" value="+" style="width:36px;height:36px;font-size:25px;z-index:20;">
  			</c:if>	
  			</td>
  		</tr>
  		</c:forEach>
  		</table>
  		<p align="right">
	  		<input type="submit" name="clear" value="Сбросить" style="width:120px;height:50px;font-size:19px;z-index:53;margin-right:15px">
  		<input type="submit" name="search" value="Найти" style="width:120px;height:50px;font-size:19px;z-index:13"></p>
  	</form:form> 
  	
  	<h1 id="Heading1">Резюме</h1><br>
  	<table border=1 cellpadding=5 style="margin-top:5px;text-allign:middle">
	      	<tr>
	      		<td><b>Работник</b></td>
	      		<td><b>Искомая должность</b></td>
	      		<td><b>Желаемая зарплата</b></td>
	      	</tr>
	      	<c:forEach items="${jhs}" var="jh">
	      	<tr>
	      		<td><a href="/hr/profile.htm?employeeID=${jh.employee.id}">${jh.employee.fullname}</a></td>
	      		<td>${jh.job.name}</td>
	      		<td>
	      			<c:if test="${jh.salaryMin != null}">
			            ${jh.salaryMin} ₽ 
			        </c:if>
			        <c:if test="${jh.salaryMin != null && jh.salaryMax != null}">
			            — 
			        </c:if> 
			        <c:if test="${jh.salaryMax != null}">
			            ${jh.salaryMax} ₽
			        </c:if>
	      		</td>
	      	</tr>
	      	</c:forEach>
	      </table><br><br><br><br>
	 </div>
  </div>
  
<%@ include file="/WEB-INF/jsp/footer.jsp" %>