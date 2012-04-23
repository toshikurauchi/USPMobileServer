<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>USP Mobile Server</title>
<style type="text/css">
table {
	width: 70%;
}
</style>
</head>
<body>
	<h2>URLs disponíveis</h2>
	
	<h3>Lista dos Bandejões</h3>
	<table>
		<tr>
			<td>URL:</td>
			<td><a href="http://valinhos.ime.usp.br:56080/usp-mobile/bandejao">http://valinhos.ime.usp.br:56080/usp-mobile/bandejao</a></td>
		</tr>
		<tr>
			<td>Método HTTP:</td>
			<td>GET</td>
		</tr>
		<tr>
			<td>JSON de resposta:</td>
			<td>{"set": ["bandejao1","bandejao2","bandejao3","...","bandejaoN"]}</td>
		</tr>
	</table>
	
	<h3>Lista dos comentários do bandejão</h3>
	<table>
		<tr>
			<td>URL:</td>
			<td>http://valinhos.ime.usp.br:56080/usp-mobile/bandejao/<b>NOME</b></td>
			<td><b>NOME</b> é algum algum dos bandejões listados ou <a href="http://valinhos.ime.usp.br:56080/usp-mobile/bandejao/mock">mock</a> (para testes)</td>
		</tr>
		<tr>
			<td>Método HTTP:</td>
			<td>GET</td>
		</tr>
		<tr>
			<td>JSON de resposta:</td>
			<td>{"list":[{"texto":"Comentário1","hora":"55-59-19-12-09-12","fila":"<b>TAMANHO</b>"},{"texto":"Comentário2","hora":"30-02-20-12-09-12","fila":"<b>TAMANHO</b>"}]}</td>
			<td><b>TAMANHO</b> é algum dos seguintes valores: SEM_FILA, PEQUENA, MEDIA, GRANDE, MUITO_GRANDE</td>
			<td>A hora segue o padrão ss-mm-HH-dd-MM-yy definido <a href="http://docs.oracle.com/javase/1.4.2/docs/api/java/text/SimpleDateFormat.html">aqui</a></td>
			<td>O resultado devolvido é a lista referente ao período atual (café, almoço ou janta). Os comentários de períodos anteriores não está disponível atualmente.</td>
		</tr>
	</table>
	
	<h3>Adicionar comentário</h3>
	<table>
		<tr>
			<td>URL:</td>
			<td>http://valinhos.ime.usp.br:56080/usp-mobile/bandejao/<b>NOME</b></td>
			<td>Idem à lista de comentários de um determinado bandejão</td>
		</tr>
		<tr>
			<td>Método HTTP:</td>
			<td>POST</td>
		</tr>
		<tr>
			<td>Content-type:</td>
			<td>application/x-www-form-urlencoded</td>
		</tr>
		<tr>
			<td>Formato:</td>
			<td>comentario.fila=<b>TAMANHO</b>&comentario.texto=seu comentário</td>
			<td><b>TAMANHO</b> conforme definido no JSON de resposta da lista de comentários</td>
		</tr>
	</table>
</body>
</html>
