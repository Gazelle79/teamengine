<%@ page language="java" session="false"%>
<%@ page import="com.leidos.te.web.controllers.AdvancedReportingServlet" %>
<%@ page import="com.leidos.te.web.models.*" %>
<%@ page import="java.util.*" %>

<jsp:useBean id="sessionId" class="java.lang.String" scope="request" />
<jsp:useBean id="testResults" class="com.leidos.te.web.models.TestResults" scope="request" />

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en-US" lang="en-US">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>TEAM Engine</title>
<link rel="stylesheet" type="text/css" href="styles/main.css">


<%-- 
Passed: #66F
Failed: #FF0
Skipped: #666
 --%>

<style>
.accordion {
    background-color: #eee;
    color: #444;
    cursor: pointer;
    padding: 18px;
    width: 100%;
    border: none;
    text-align: left;
    outline: none;
    font-size: 12px;
    transition: 0.4s;
}

.active, .accordion:hover {
    background-color: #ccc; 
}

.panel {
    padding: 0 18px;
    background-color: white;
    display: none;
    overflow: hidden;
    transition: max-height 0.2s ease-out;
}

.accordion:after {
    content: '\02795'; /* Unicode character for "plus" sign (+) */
    font-size: 10px;
    color: #777;
    float: left;
    margin-left: 5px;
}

.active:after {
    content: "\2796"; /* Unicode character for "minus" sign (-) */
}

.showpanel{
	display: block !important;
}

</style>

</head>
<body>
	<%@ include file="header.jsp"%>
	
	<!-- <form action="advancedReporting" method="post">  -->
			<input name ="action" type="hidden" id="action" value="update"></input>
			<input name="sessionId" type="hidden" id="sessionId" value="<%= sessionId  %>"></input>	
			<h2>Test Results for session <%= sessionId %></h2>
		
			<% if (testResults!= null)
				{
				ArrayList<TestSuite> testSuiteList = testResults.getTestSuites();
				TestSuite thisTestSuite = testSuiteList.get(0);
				
				ArrayList<Test> testList = thisTestSuite.getTests();
				ArrayList<TestClass> testClassList = new ArrayList<TestClass>();
				ArrayList<TestMethod> testMethodList = new ArrayList<TestMethod>();
			%>
				<table id="ResultTables" style="width: 400px;">
				<tr><td><b>Test Name: </b></td><td><%= thisTestSuite.getName() %></td></tr>					
				<tr><td><b>URL: </b></td><td> <%= thisTestSuite.getTestUrl() %>r</td></tr>				
				<tr><td><b>   </b></td><td>   </td></tr>
				<tr><td><b>Start Time: </b></td><td><%= thisTestSuite.getStartTime() %></td></tr>
				<tr><td><b>End Time: </b></td><td><%= thisTestSuite.getEndTime() %></td></tr>
				<tr><td><b>Start Date: </b></td><td><%= thisTestSuite.getStartDate() %></td></tr>
				<tr><td><b>End Date: </b></td><td><%= thisTestSuite.getEndDate() %></td></tr>				
				</table>	
				
				<br/>
				<br/>
			
				<h3>Core Conformance classes</h3>
				<% for(Test thisTest : testList)
					{ %>
					<button class="accordion"><%= thisTest.getName() %></button>	
					<div class="panel">
					<% for(TestClass thisTestClass : thisTest.getTestClasses())
						{ %>
						<button class="accordion"><%= thisTestClass.getName() %> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Count: <%= thisTestClass.getTotalTestCount() %> tests. &nbsp;&nbsp; Passed: <%= thisTestClass.getPassedTestPercentage() %>% &nbsp;&nbsp; Failed: <%= thisTestClass.getFailedTestPercentage() %>%  &nbsp;&nbsp; Skipped: <%= thisTestClass.getSkippedTestPercentage() %>%  </button>	
						<div class="panel">
						
						<% if (thisTestClass.passedTests.size() > 0) 
						{ %>
						<%-- Code for passed tests is here. --%>
						<button class="accordion">Passed tests &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <%= thisTestClass.getPassedTestPercentage() %>% (<%= thisTestClass.passedTests.size() %> tests)  </button>	
						<div class="panel">
							<% for(TestMethod thisTestMethod : thisTestClass.passedTests)
								{ %>			
							<button class="accordion"><%= thisTestMethod.getName() %></button>	
							<div class="panel">
							
								<p>Method name: <%= thisTestMethod.getName() %></p>
								<p>Final Result: <%= thisTestMethod.getTestStatus() %></p>
								<p>Path: <%= thisTestClass.getPath() %></p>
								<p>Class name: <%= thisTestClass.getName() %></p>
																
							</div>
							<% } %>
						</div> <%-- for passed tests div/panel --%>
						<% } %>
						
						
						<% if (thisTestClass.failedTests.size() > 0) 
						{ %>							
						<%-- Code for failed tests is here. --%>
						<button class="accordion">Failed tests  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <%= thisTestClass.getFailedTestPercentage() %>% (<%= thisTestClass.failedTests.size() %> tests)    </button>	
						<div class="panel">
							<% for(TestMethod thisTestMethod : thisTestClass.failedTests)
								{ 
								%>			
							<button class="accordion"><%= thisTestMethod.getName() %></button>	
							<div class="panel">
								<p>Method name: <%= thisTestMethod.getName() %></p>
								<p>Final Result: <%= thisTestMethod.getTestStatus() %></p>
								<p>Path: <%= thisTestClass.getPath() %></p>
								<p>Class name: <%= thisTestClass.getName() %></p>
								<p>Reason(s) for failure:</p>
								<ul> 
								<% for (ExceptionType thisException : thisTestMethod.getExceptions())
									{ %> 
									 <li><%= thisException.getMessage() %></li> 
								<% } %>
								</ul>
								<p>Test parameters / requests: </p>
								
								<%
									for(AttributeCollection thisAttributeCollection: thisTestMethod.getAttributeCollections())
									{ 
										for(Attribute thisAttribute: thisAttributeCollection.getAttributes())
										{
								%>
								
									 <p> <%= thisAttribute.getData() %></p>
									<% } //end attribute
									} //end attribute collection %>	
							</div>
							<% } %> <%-- end loop for failed tests --%>
						</div> <%-- for failed tests div/panel --%>
						<% } %>


						<% if (thisTestClass.skippedTests.size() > 0) 
						{ %>
						<%-- Code for skipped tests is here. --%>
						<button class="accordion">Skipped tests  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <%= thisTestClass.getSkippedTestPercentage() %>% (<%= thisTestClass.skippedTests.size() %> tests)    </button>	
						<div class="panel">
							<% for(TestMethod thisTestMethod : thisTestClass.skippedTests)
								{ %>			
							<button class="accordion"><%= thisTestMethod.getName() %></button>	
							<div class="panel">
								
								<p>Method name: <%= thisTestMethod.getName() %></p>
								<p>Final Result: <%= thisTestMethod.getTestStatus() %></p>
								<p>Path: <%= thisTestClass.getPath() %></p>
								<p>Class name: <%= thisTestClass.getName() %></p>
								<p>Reason(s) for exclusion:</p>
								<ul> 
								<% for (ExceptionType thisException : thisTestMethod.getExceptions())
									{ %> 
									 <li><%= thisException.getMessage() %></li> 
								<% } %>
								</ul>
								
							</div>
							<% } %>
						</div> <%-- for skipped tests div/panel --%>
						<% } %>	
							
										
						</div>
						<% } // end test class %>
						 
				      </div>  
				<% } //end test List %>
				
			<% } %>
			
			<br/>
			<br/>
	
	<!-- </form> -->
	
<script>
var acc = document.getElementsByClassName('accordion');
var i;
   
for (i = 0; i < acc.length; i++) 
{
  acc[i].addEventListener("click", function() 
  {
    this.classList.toggle("active");
    var panel = this.nextElementSibling;
	panel.classList.toggle("showpanel");
  });
}
</script>
      
	
	<%@ include file="footer.jsp"%>
</body>
</html>
