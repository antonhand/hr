<%@ include file="/WEB-INF/jsp/header.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
  <div id="container">
	  	<form:form method="get" action="/hr/addCompany.htm">
	  		<input type="hidden" name="companyID" value="${comp.id}">
		  	<input type="submit" id="Button2" value="Редактировать" style="position:absolute;right:38px;top:120px;width:192px;height:36px;font-size:17px;z-index:52;">
		</form:form>
  
  	  <div id="wb_Heading1" style="position:absolute;left:14px;top:114px;width:1148px;height:auto;z-index:15;">
         <h1 id="Heading1">${comp.name}</h1><br>
         <br>
	      Вакансии<br>
	      <table border=1 style="margin-top:5px">
	      	<tr>
	      		<td style="padding: 5px;border-right:0px"><b>Должность</b></td>
	      		<td style="padding: 5px;border-left:0px;border-right:0px"><b>Зарплата</b></td>
	      		<td style="padding: 5px;border-left:0px;border-right:0px"><b>Образование</b></td>
	      		<td style="width:200px;padding: 0px;border-left:0px"></td>
	      	</tr>
	      	<c:forEach items="${comp.vacancies}" var="vac">
	      	<tr>
	      		<td style="padding: 5px;">${vac.job.name}</td>
	      		<td style="padding: 5px;">${vac.salary} ₽</td>
	      		<td style="padding: 5px;">${vac.education.name}</td>
	      		<td style="padding: 0px">
	      		<c:if test="${!add}">
	      			<form:form method="get" action="/hr/searchEmployee.htm">
		      			<input type="hidden" name="vacancyID" value="${vac.id}">
		      			<input type="submit" value="Найти резюме" style="width:200px;height:36px;font-size:17px;border-radius: 0px;">
	      			</form:form>
	      		</c:if>
	      		</td>
	      	</tr>
	      	<tr>
	      		<td colspan="4" style="padding: 5px;">
	      			Опыт работы<br>
	      			<ul>
	      				<c:forEach items="${vac.vacancyReqs}" var="vr">
	      					<li><b>${vr.job.name}</b>: ${vr.expMonths} месяцев</li>
	      				</c:forEach>
	      			</ul>
	      		</td>
	      	</tr>
	      	</c:forEach>
	      	<c:if test="${add}">
	      	<form:form modelAttribute="vac" method="post" action="/hr/com.profile.htm">
	      	<tr>
	      		<td style="padding: 5px;">
	      			<form:input path="job.name" style="width: 95%;height:27px;line-height:26px;font-size:14px;"/><br>
	      			<form:errors path="job.name" cssClass="errors" style="font-size:12px;color:red;width:80%;z-index:3;"/>
	      		</td>
	      		<td style="padding: 5px;">
	      			<form:input path="salary" style="width: 95%;height:27px;line-height:26px;font-size:14px;"/><br>
	      			<form:errors path="salary" cssClass="errors" style="font-size:12px;color:red;width:80%;z-index:3;"/>
	      		</td>
	      		<td style="padding: 5px;">
	      			<form:select path="education.id" items="${educations}" style="width: 95%;height:37px;line-height:26px;font-size:14px;"/><br>
	      		</td>
	      		<td style="padding: 0px">
		      			<form:input type="hidden" path="company.id" value="${comp.id}"/>
		      			<input type="submit" name="save" value="Сохранить" style="width:200px;height:48px;font-size:17px;border-radius: 0px;">
	      		</td>
	      	</tr>
	      	<tr>
	      		<td colspan="4" style="padding: 5px;">
	      			<b>Опыт работы</b><br>
			  		<table style="margin-top:6px">
			  		<tr>
			  			<td style="padding-bottom:6px">Должность</td>
			  			<td style="padding-bottom:6px">Стаж (месяцев)</td>
			  			<td style="width:26px"></td>
			  		</tr>
			  		<c:forEach items="${vac.vacancyReqs}" var="vr" varStatus="status">
			  		<tr>
			  			<td>
			  				<input type="text" name="vacancyReqs[${status.index}].job.name" id="Editbox4" style="width:95%;height:27px;line-height:26px;font-size:14px;z-index:2;" value="${vr.job.name}" spellcheck="true"/>
			  				<form:errors path="vacancyReqs[${status.index}].job.name" cssClass="errors" style="font-size:12px;color:red;width:80%;z-index:3;"/>
			  			</td>
			  			<td>
			  				<input type="text" name="vacancyReqs[${status.index}].expMonths" id="Editbox6" style="width:95%;height:27px;line-height:26px;font-size:14px;z-index:2;" value="${vr.expMonths}" spellcheck="true"/><br>
			  				<form:errors path="vacancyReqs[${status.index}].expMonths" cssClass="errors" style="font-size:12px;color:red;width:80%;z-index:3;"/>
			  			</td>
			  			<td>
			  			<c:if test="${status.last}">
			  				<input type="submit" name="addVR" value="+" style="width:36px;height:36px;font-size:25px;z-index:20;">
			  			</c:if>	
			  			</td>
			  		</tr>
			  		</c:forEach>
			  		</table>
	      		</td>	
	      	</tr>
	      	</form:form>
	      	</c:if>
	      </table>
	      <c:choose>
	          <c:when test="${add}">
	            <form:form modelAttribute="company" method="post" action="/hr/com.profile.htm">
	            	<form:input type="hidden" path="id" value="${comp.id}"/>
	            	<p align="right"><input type="submit" name="cancel" value="Отмена" style="width:120px;height:50px;font-size:19px;z-index:13"></p>
	            </form:form>
	          </c:when>
	          <c:otherwise>
	          	<form:form modelAttribute="company" method="post" action="/hr/com.profile.htm">
	          		<form:input type="hidden" path="id" value="${comp.id}"/>
	            	<p align="right"><input type="submit" name="add" value="Добавить" style="width:120px;height:50px;font-size:19px;z-index:13"></p>
	            </form:form>
	          </c:otherwise>
	        </c:choose>
	      <br>
	      <br><br><br><br><br>
      </div>
  </div>    
   
<%@ include file="/WEB-INF/jsp/footer.jsp" %>