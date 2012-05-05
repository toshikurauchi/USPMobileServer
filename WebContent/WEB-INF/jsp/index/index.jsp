<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>USP Mobile Server</title>
<style type="text/css">
table {
	width: 70%;
}
td {
	border: 1px solid #999;
}
</style>
</head>
<body>
	<h2>URLs disponíveis</h2>
	
	<h3>Bandejão</h3>
	
	<h4>Lista dos Bandejões</h4>
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
	
	<h4>Lista dos comentários do bandejão</h4>
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
	
	<h4>Adicionar comentário</h4>
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
	
	<h4>Período de refeição</h4>
	<table>
		<tr>
			<td>URL:</td>
			<td>http://valinhos.ime.usp.br:56080/usp-mobile/bandejao/<b>NOME</b>/periodo</td>
			<td><b>NOME</b> é algum algum dos bandejões listados ou <a href="http://valinhos.ime.usp.br:56080/usp-mobile/bandejao/mock">mock</a> (para testes)</td>
		</tr>
		<tr>
			<td>Método HTTP:</td>
			<td>GET</td>
		</tr>
		<tr>
			<td>Resposta:</td>
			<td><b>PERIODO</b></td>
			<td><b>PERIODO</b> é algum dos seguintes valores: CAFE, ALMOCO, JANTAR</td>
		</tr>
	</table>
	
	<h3>Portões</h3>
	
	<h4>Adicionar comentário do portão</h4>
	<table>
		<tr>
			<td>URL:</td>
			<td>http://valinhos.ime.usp.br:56080/usp-mobile/portao/<b>NUMERO</b>/<b>SENTIDO</b></td>
			<td><b>NUMERO</b> é o número do portão (1,2,3 ou 100 para mock)</td>
			<td><b>SENTIDO</b> é sentido ENTRANDO ou SAINDO</td>
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
			<td>Formato (TODOS os parâmetros são opcionais!):</td>
			<td>comentario.timestamp=<b>TIMESTAMP</b>&comentario.latitude=<b>LATITUDE</b>&comentario.longitude=<b>LONGITUDE</b>&comentario.comentario=algum comentário</td>&comentario.idDoUsuario=<b>ID_DO_USUARIO</b>&comentario.distanciaAoPortao=<b>DISTANCIA</b>
			<td><b>TIMESTAMP</b> timestamp em milisegundos do comentário (se não for fornecido será utilizada a hora de chegada no servidor)</td>
			<td><b>LATITUDE</b> e <b>LONGITUDE</b> doubles com a latitude e a longitude do momento do comentário</td>
			<td><b>ID_DO_USUARIO</b> string de identificação do usuário (para atualização de posição)</td>
			<td><b>DISTANCIA</b> double com a distância ao portão</td>
		</tr>
	</table>
	
	<h4>Lista dos comentários do portão</h4>
	<table>
		<tr>
			<td>URL:</td>
			<td>http://valinhos.ime.usp.br:56080/usp-mobile/portao/<b>NUMERO</b>/<b>SENTIDO</b></td>
			<td><b>NUMERO</b> é o número do portão (1,2,3 ou 100 para mock)</td>
			<td><b>SENTIDO</b> é sentido ENTRANDO ou SAINDO</td>
		</tr>
		<tr>
			<td>Método HTTP:</td>
			<td>GET</td>
		</tr>
		<tr>
			<td>Parâmetro opcional:</td>
			<td>aPartirDe=<b>TIMESTAMP</b></td>
			<td><b>TIMESTAMP</b> é um valor em milisegundos. Serão listados todos os comentários desse portão a partir desse <b>TIMESTAMP</b>. Caso esse parâmetro não seja fornecido serão listados os comentários da última hora.</td>
		</tr>
		<tr>
			<td>JSON de resposta:</td>
			<td>{"list":[{"numero":<b>NUMERO_DO_PORTAO</b>, "timestamp":<b>TIMESTAMP</b>, "latitude":<b>LATITUDE</b>, "longitude":<b>LONGITUDE</b>, "comentario":"algum comentario", "idDoUsuario":"<b>ID_DO_USUARIO</b>", "distanciaAoPortao":<b>DISTANCIA</b>}, {"numero":<b>NUMERO_DO_PORTAO</b>, "timestamp":<b>TIMESTAMP</b>}]}</td>
			<td><b>NUMERO_DO_PORTAO</b> é 1, 2, 3, ou 100</td>
			<td><b>TIMESTAMP</b> é o timestamp em milisegundos do comentário</td>
			<td><b>LATITUDE</b> e <b>LONGITUDE</b> são os valores na hora do comentário (tipo double)</td>
			<td><b>ID_DO_USUARIO</b> string de identificação do usuário (para atualização de posição)</td>
			<td><b>DISTANCIA</b> double com a distância ao portão</td>
			<td>Os únicos valores que sempre estarão presentes são o número do portão e o timestamp. Todos os outros valores são opcionais.</td>
		</tr>
	</table>
</body>
</html>
