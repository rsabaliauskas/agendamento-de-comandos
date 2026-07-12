Projeto Agendamento de Comandos
===============================

Eu uso linux a quase 30 anos e nele existe como agendamento de execução de comandos o "cron" a nível do sistema e o comando "at" a nível de usuário. 
Porém, em alguns casos este programa pode ser útil.

Esse programa suporta ano, mês, dia, hora e minutos e permite o uso de coringa (letra "x") pra agendamentos multiplos.

1 exemplo de entrada de ação no comando.txt:

  2026;;07;;x;;x;;x;;"notify-send" "-u" "critical" "-t" "0" "\\"hora do cafe"\\"
  
  No exemplo acima o comando notify-send...  será executado todos os dias, todas as hora e todos os minutos do mês 07/2026.
  
2 exemplo de entrada de ação no comando.txt:  

  2026;;07;;12;;20;;x;;"notify-send" "-u" "critical" "-t" "0" "\"hora do cafe\"
  
  No exemplo acima o comando notify-send...  será executado todos os minutos da hora 23 do mês 12/07/2026.
  
  
  os campos estão dividos por ";;" e:
   
 - 1º campo é o ano, com 4 dígitos, ex. 2026.
 - 2º campo é o mês, com dois dígitos, ex. 07.
 - 3º campo é o dia, com dois dígitos, ex. 01.
 - 4º campo é o hora, com dois dígitos, ex. 04, 18.
 - 5º campo é o minutos, com dois dígitos, ex.01, 12.
 - 6º campo é o comando a ser executado, com cada palavra escrito entre aspas dupas e caso o comando já tenha aspas então substituir por "\\".
 
 Ao executá-lo ele rodará em loop de 60 em 60 segundos (1 minutos) e em vista disso ele pode apresentar um atraso de 59 segundos, dependendo do momento em que é executado.
 
 Depois de executado e estar rodando em loop e lendo o aquivo comandos.txt, é possível acrescentar ou retirar entradas de datas/horas/comandos do arquivo comandos.txt
