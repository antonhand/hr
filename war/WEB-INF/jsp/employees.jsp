<%@ include file="/WEB-INF/jsp/header.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   
   <div id="container">
      <div id="wb_Form1" style="position:absolute;left:0px;top:100px;width:1200px;height:93px;z-index:7;">
         <form:form modelAttribute="emp" method="post">
            <form:input path="surname" id="Editbox4" style="position:absolute;left:0px;top:28px;width:1085px;height:26px;line-height:26px;font-size:14px;z-index:2;" value="" spellcheck="false"/>
            <input type="submit" id="Button2" value="Найти" style="position:absolute;left:1104px;top:28px;width:96px;height:36px;font-size:17px">
         </form:form> 
      </div>
      <form method="get" action="/hr/profile.htm" name="">
	      <select name="employeeID" size="16" style="position:absolute;left:0px;top:193px;width:1202px;z-index:6;font-size:20px">
	      <c:forEach items="${employee}" var="empl">
	         <option value="${empl.id}">${empl.fullname}</option>
	      </c:forEach> 
	      </select> 
	      <div id="wb_Form2" style="position:absolute;left:0px;top:600px;width:120px;height:93px;z-index:7;">
	      	<input type="submit" id="Button3" name="" value="Просмотр" style="position:absolute;left:0px;top:28px;width:120px;height:50px;font-size:17px">
	      </div>
      </form>
      <div id="wb_Form3" style="position:absolute;left:1080px;top:600px;width:120px;height:93px;z-index:7;">
         <form name="Form3" method="GET" action="<c:url value="/addEmployee.htm"/>">
             <input type="submit" id="Button4" name="" value="Добавить" style="position:absolute;left:0px;top:28px;width:120px;height:50px;font-size:17px">
         </form> 
      </div>
   </div>
<%@ include file="/WEB-INF/jsp/footer.jsp" %>