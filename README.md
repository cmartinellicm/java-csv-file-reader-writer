# Atividades Let's Code

Atividade do curso Santander Coders | Web Full-Stack da Let's Code (Módulo 7 - JAVA: Dependências Externas).

## Objetivos

- Manipulação de arquivos 
- Aplicação de POO
- Uso de ferramenta de build/gerenciador de pacotes: Maven ou Gradle
- Uso de dependências
- Uso de estruturas de dados

### Requisitos da atividade:

- Ler o arquivo CSV fornecido em uma estrutura de dados;
- Remover os registros duplicados(se houver);
- Ordenar por data/hora, time_1 e time_2;
- Subdividir a estrutura de dados por time mantendo as ordens anteriores e gerar um arquivo por time contendo o histórico dos jogos ordenados por data;
- Gerar um arquivo contendo a tabela de classificação final dos times, ordenado do que tiver mais pontos para o que tiver menos pontos (lembrando que: vitoria = 3pts, empate = 1pt, derrota = 0pt).
  - Identificar a quantidade de vitórias, empates e derrotas de cada time. 
  - O arquivo com a tabela de classificação final deverá ser gerado no formato .csv, utilizando o separador ";", os demais poderão ser .txt.

#### Modelo arquivo por time:
01/01/22, 21h00: Sao Paulo 5 x 0 Corinthians  
01/02/22, 21h00: Sao Paulo 3 x 2 Palmeiras  
...

#### Modelo Arquivo resultado final:
Time;Vitorias;Empates;Derrotas;Pontos  
Bragantino;21;6;11;69  
Fluminense;19;6;13;63  
Fortaleza;19;5;14;62  
...
