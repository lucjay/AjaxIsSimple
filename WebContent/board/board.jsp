<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		String bNo = request.getParameter("boardNo");
	%>

	<h2>원본글</h2>
	<p id="origin"></p>
	<br>
	<br>
	<br>
	<h2>댓글</h2>
	<p id="reply"></p>

	<script>
		var orig = document.getElementById('origin');
		var rep = document.getElementById('reply');
		showBoard();
		function showBoard() {
			var xhtp = new XMLHttpRequest();
			xhtp.onreadystatechange = function() {
				if (xhtp.readyState == 4 && xhtp.status == 200) {
					createBoard(xhtp);
				}
			}
			xhtp.open('get', '../BoardServlet?boardNo=' +
	<%=bNo%>
		);
			xhtp.send();
		}
		function createBoard(obj) {
			var result = JSON.parse(obj.responseText);
			console.log(result);
			var ktitle = [ "글번호", "작성자", "제목", "내용", "작성일자" ];
			var otitle = [ "bNo", "writer", "title", "content", "cDate" ];
			var $table, $th, $td, $tr, $text;
			//원본글
			$table = document.createElement("table");
			$table.setAttribute('border', 1);
			for (i in otitle) {
				$tr = document.createElement("tr");
				$text = document.createTextNode(ktitle[i]);
				$td = document.createElement("td");
				$td.appendChild($text);
				$tr.appendChild($td);
				$text = document.createTextNode(result[otitle[i]]);
				$td = document.createElement("td");
				$td.appendChild($text);
				$tr.appendChild($td);
				$table.appendChild($tr);

			}

			orig.appendChild($table);
			var rtitle = [ "rbNo", "rcont", "rwrit", "rdate", "tpNo" ];
			//댓글
			$table = document.createElement("table");
			$table.setAttribute('border', 1);
			for (i in result.rList){
				$tr = document.createElement("tr");
				for(r of rtitle){
					$text = document.createTextNode(result.rList[i][r]);
					$td = document.createElement("td");
					$td.appendChild($text);
					$tr.appendChild($td);
				}
				$table.appendChild($tr);
			}
			rep.appendChild($table);
		}
	</script>

</body>
</html>