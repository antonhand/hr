<%@ include file="/WEB-INF/jsp/header.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
  <div id="container">
  	<form:form method="get" action="/hr/addEmployee.htm">
  		<input type="hidden" name="employeeID" value="${emp.id}">
	  	<input type="submit" id="Button2" value="Редактировать" style="position:absolute;right:38px;top:120px;width:192px;height:36px;font-size:17px;z-index:52;">
	</form:form>
  
  	  <div id="wb_Heading1" style="position:absolute;left:14px;top:114px;width:1148px;height:auto;z-index:15;">
         <h1 id="Heading1">${emp.fullname}</h1><br>
         <br>
	      Образование:<br>
	      <b>${emp.education.name}</b><br>
	      <br>
	      Адрес:<br>
	      <b>${emp.address}</b><br>
	      <br>
	      История трудоустройства<br>
	      <table border=1 cellpadding=5 style="margin-top:5px;text-allign:middle">
	      	<tr>
	      		<td><b>Компания</b></td>
	      		<td><b>Должность</b></td>
	      		<td><b>Зарплата</b></td>
	      		<td><b>Время работы</b></td>
	      	</tr>
	      	<c:forEach items="${emp.jobStory}" var="js">
	      	<tr>
	      		<td>${js.company.name}</td>
	      		<td>${js.job.name}</td>
	      		<td>${js.salary} ₽</td>
	      		<td><fmt:formatDate value="${js.startDate}" pattern="dd.MM.yyyy"/> — <fmt:formatDate value="${js.endDate}" pattern="dd.MM.yyyy"/> 
	      			<c:if test="${js.endDate == null}">
	      				н.в.
	      			</c:if>
	      		</td>
	      	</tr>
	      	</c:forEach>
	      	<c:if test="${addJS}">
	          	<tr>
	          	<form:form modelAttribute="js" method="post" action="/hr/profile.htm">
	      			<td><form:select path="company.id" items="${companies}" style="width: 95%;height:37px;line-height:26px;font-size:14px;"/></td>
	      			<td><form:input path="job.name" style="width: 95%;height:27px;line-height:26px;font-size:14px;"/><br>
	      				<form:errors path="job.name" cssClass="errors" style="font-size:12px;color:red;width:80%;z-index:3;"/>
	      			</td> 
	      			<td><form:input  path="salary" style="width: 95%;height:27px;line-height:26px;font-size:14px;"/><br>
	      				<form:errors path="salary" cssClass="errors" style="font-size:12px;color:red;width:80%;z-index:3;"/>
	      			</td>
	      			<td>
	      				<form:input type="hidden" path="employee.id" value="${emp.id}"/>
	      				<form:input  path="startDate" placeholder="ДД.ММ.ГГГГ" pattern="\d\d.\d\d.\d\d\d\d" style="width: 44%;height:27px;line-height:26px;font-size:14px;"/>  — 
	      				<form:input  path="endDate" placeholder="ДД.ММ.ГГГГ" pattern="\d\d.\d\d.\d\d\d\d" style="width: 44%;height:27px;line-height:26px;font-size:14px;"/><br>
	      				<form:errors path="startDate" cssClass="errors" style="font-size:12px;color:red;width:80%;z-index:3;"/>
	      			</td>
	      		</form:form>
	      		<tr>
	         </c:if> 
	      </table>
	      <c:if test="${!addJH}">
	      <c:choose>
	          <c:when test="${addJS}">
	            <form:form modelAttribute="employee" method="post" action="/hr/profile.htm">
	            	<form:input type="hidden" path="id" value="${emp.id}"/>
	            	<p align="right"><input type="submit" name="cancelJS" value="Отмена" style="width:120px;height:50px;font-size:19px;z-index:13">
	            	<input type="submit" form="js" name="saveJS" value="Сохранить" style="width:120px;height:50px;font-size:19px;z-index:13">
	            	</p>
	            </form:form>
	          </c:when>
	          <c:otherwise>
	          	<form:form modelAttribute="employee" method="post" action="/hr/profile.htm">
	          		<form:input type="hidden" path="id" value="${emp.id}"/>
	            	<p align="right"><input type="submit" name="addJS" value="Добавить" style="width:120px;height:50px;font-size:19px;z-index:13"></p>
	            </form:form>
	          </c:otherwise>
	        </c:choose>
	        </c:if>
	      Резюме<br>
	      <table border=1 style="margin-top:5px">
	      <tr>
	      		<td style="padding: 5px;border-right:0px"><b>Должность</b></td>
	      		<td style="padding: 5px;border-left:0px;border-right:0px"><b>Зарплата</b></td>
	      		<td style="width:200px;padding: 0px;border-left:0px;"></td>
	      	</tr>
	      	<c:forEach items="${emp.jobHunt}" var="jh">
	      	<tr>
	      		<td style="padding: 5px;">${jh.job.name}</td>
	      		<td style="padding: 5px;">
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
	      		<td style="padding: 0px">
	      		<c:if test="${!addJH && !addJS}">
		      		<form:form method="get" action="/hr/searchCompany.htm">
		      			<input type="hidden" name="jhEmployeeID" value="${jh.employee.id}">
		      			<input type="hidden" name="jhJobID" value="${jh.job.id}">
		      			<input type="submit" value="Найти вакансии" style="width:200px;height:36px;font-size:17px;border-radius: 0px;">
		      		</form:form>
		      	</c:if>

			</tr>
	      	</c:forEach>
	      	
	      	<c:if test="${addJH}">
	          	<tr>
	          	<form:form modelAttribute="jh" method="post" action="/hr/profile.htm">
	      			<td><form:input path="job.name" style="width: 95%;height:27px;line-height:26px;font-size:14px;"/><br>
	      			<form:errors path="job.name" cssClass="errors" style="font-size:12px;color:red;width:80%;z-index:3;"/>
	      			</td>
	      			<td><form:input path="salaryMin" style="width: 45%;height:27px;line-height:26px;font-size:14px;"/>  — 
	      			<form:input  path="salaryMax" style="width: 45%;height:27px;line-height:26px;font-size:14px;"/></td>
	      			<td style="padding: 0px">
	      				<form:input type="hidden" path="employee.id" value="${emp.id}"/>
	      				<input type="submit" name="saveJH" value="Сохранить" style="width:200px;height:58px;font-size:17px;border-radius: 0px;">
	      			</td>
	      		</form:form>
	      		<tr>
	         </c:if>   
	      </table>
	      <c:if test="${!addJS}">
	      <c:choose>
	          <c:when test="${addJH}">
	            <form:form modelAttribute="employee" method="post" action="/hr/profile.htm">
	            	<form:input type="hidden" path="id" value="${emp.id}"/>
	            	<p align="right"><input type="submit" name="cancelJH" value="Отмена" style="width:120px;height:50px;font-size:19px;z-index:13"></p>
	            </form:form>
	          </c:when>
	          <c:otherwise>
	          	<form:form modelAttribute="employee" method="post" action="/hr/profile.htm">
	          		<form:input type="hidden" path="id" value="${emp.id}"/>
	            	<p align="right"><input type="submit" name="addJH" value="Добавить" style="width:120px;height:50px;font-size:19px;z-index:13"></p>
	            </form:form>
	          </c:otherwise>
	        </c:choose>
	        </c:if>
	      <br>
	      <br><br><br><br><br>
      </div>
  </div>    
   
<%@ include file="/WEB-INF/jsp/footer.jsp" %>