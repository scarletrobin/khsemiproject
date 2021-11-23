package report.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import report.model.service.ReportService;
import report.model.vo.ChartData;

/**
 * Servlet implementation class ChartDataServlet
 */
@WebServlet(name = "ChartData", urlPatterns = { "/chartData" })
public class ChartDataServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChartDataServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				//1.인코딩
				request.setCharacterEncoding("utf-8");
				//2.값추출
				//3.비즈니스로직
				int num = Integer.parseInt(request.getParameter("num"));
				String time1="";
				String time2="";
				if(num==3) {
				 	Calendar cal = Calendar.getInstance();
			        cal.setTime(new Date()); //현재날짜 셋팅
			        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd"); //원하는 포맷셋팅
			        time1=df.format(cal.getTime()); //현재 날짜 
			        cal.add(Calendar.DATE, -30);
			        time2=df.format(cal.getTime()); //30일전 날짜
				}else {
					time1=request.getParameter("end");
					time2=request.getParameter("start");
				}
					ArrayList<ChartData> list = new ReportService().chartData(time1,time2);

				//4.결과처리
				JSONObject data = new JSONObject();
				JSONObject ajaxObjCols1 = new JSONObject();    //cols의 1번째 object를 담을 JSONObject
				JSONObject ajaxObjCols2 = new JSONObject();    //cols의 2번째 object를 담을 JSONObject
				JSONArray ajaxArryCols = new JSONArray();
				JSONArray ajaxArryRows = new JSONArray();
				
				ajaxObjCols1.put("type", "string"); //이름내용
				ajaxObjCols1.put("label", "가입일"); //컬럼이름

				ajaxObjCols2.put("type", "number"); //컬럼이름내용
				ajaxObjCols2.put("label", "가입자수"); //컬럼이름

				ajaxArryCols.add(ajaxObjCols1);
				ajaxArryCols.add(ajaxObjCols2); //컬럼완성

				if(!list.isEmpty()) {
					for(ChartData cd :list) {
					/*	JSONObject obj = new JSONObject();
						obj.put("enrollDate", cd.getDate());
						obj.put("joinCnt", cd.getJoincnt());
						chartArray.add(obj);*/
						
						JSONObject legend = new JSONObject();
					    legend.put("v", cd.getDate());
					    legend.put("f", null);
					    
					    JSONObject value = new JSONObject();
					    value.put("v", cd.getJoincnt());
					    value.put("f", null);
					 
					    JSONArray cValueArry = new JSONArray();
					    cValueArry.add(legend);
					    cValueArry.add(value);
					 
					    JSONObject cValueObj = new JSONObject();
					    cValueObj.put("c", cValueArry);
					 
					    ajaxArryRows.add(cValueObj);

					}
				}
				data.put("cols", ajaxArryCols);
				data.put("rows", ajaxArryRows);

				response.setContentType("application/json");
				response.setCharacterEncoding("utf-8");
				PrintWriter out = response.getWriter();
				out.print(data);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}