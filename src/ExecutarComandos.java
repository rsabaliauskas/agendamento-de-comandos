import java.nio.file.Files;
import java.nio.file.Path;
import java.io.IOException;
import java.util.List;
import java.time.LocalDateTime;

public class ExecutarComandos {

    public static void executar() throws IOException {

        // Pega a data e a hora atuais
        LocalDateTime agora = LocalDateTime.now();

        // Transforma o ano em texto
        String anoAgora = String.valueOf(agora.getYear());

        // %02d faz o número ter dois dígitos:
        // 7 vira "07"
        String mesAgora    = String.format("%02d", agora.getMonthValue());
        String diaAgora    = String.format("%02d", agora.getDayOfMonth());
        String horaAgora   = String.format("%02d", agora.getHour());
        String minutoAgora = String.format("%02d", agora.getMinute());

        // Monta apenas uma mensagem para mostrar o horário procurado
        String procurarData =
                anoAgora + ";;" +
                mesAgora + ";;" +
                diaAgora + ";;" +
                horaAgora + ";;" +
                minutoAgora;

        System.out.println("\n[Buscando]: " + procurarData);

        // Representa o caminho do arquivo
        Path arquivoComandos = Path.of("comandos.txt");

        // Verifica se o arquivo existe
        if (!Files.exists(arquivoComandos)) {
            System.out.println(
                    "[ERRO]: Arquivo comandos.txt não encontrado."
            );
            return;
        }

        // Lê todas as linhas do arquivo
        List<String> linhas = Files.readAllLines(arquivoComandos);

        // Percorre cada linha do arquivo
        for (String linha : linhas) {

            // Remove espaços do começo e do final
            linha = linha.trim();

            // Ignora linhas vazias
            if (linha.isEmpty()) {
                continue;
            }

            /*
             * Divide a linha em no máximo 6 partes.
             *
             * Exemplo:
             * 2026;;07;;12;;17;;30;;firefox
             *
             * partes[0] = ano
             * partes[1] = mês
             * partes[2] = dia
             * partes[3] = hora
             * partes[4] = minuto
             * partes[5] = comando
             */
            String[] partes = linha.split(";;", 6);

            // Se tiver menos de 6 partes, a linha está incompleta
            if (partes.length < 6) {
                System.out.println(
                        "[Aviso]: Linha inválida: " + linha
                );
                continue;
            }

            // Remove possíveis espaços de cada campo
            String anoArquivo    = partes[0].trim();
            String mesArquivo    = partes[1].trim();
            String diaArquivo    = partes[2].trim();
            String horaArquivo   = partes[3].trim();
            String minutoArquivo = partes[4].trim();

            /*
             * Cada campo será válido quando:
             *
             * 1. possuir "x", que funciona como coringa;
             * ou
             * 2. for igual ao valor atual.
             */
            boolean anoValido =
                    anoArquivo.equalsIgnoreCase("x")
                    || anoArquivo.equals(anoAgora);

            boolean mesValido =
                    mesArquivo.equalsIgnoreCase("x")
                    || mesArquivo.equals(mesAgora);

            boolean diaValido =
                    diaArquivo.equalsIgnoreCase("x")
                    || diaArquivo.equals(diaAgora);

            boolean horaValida =
                    horaArquivo.equalsIgnoreCase("x")
                    || horaArquivo.equals(horaAgora);

            boolean minutoValido =
                    minutoArquivo.equalsIgnoreCase("x")
                    || minutoArquivo.equals(minutoAgora);

            // Executa somente quando todos os campos forem válidos
            if (anoValido
                    && mesValido
                    && diaValido
                    && horaValida
                    && minutoValido) {

                // O comando está na sexta posição, índice 5
                String comando = partes[5].trim();

                System.out.println(
                        "[Executando]: " + comando
                );

                // Envia o comando para o Bash do Linux
                ProcessBuilder pb =
                        new ProcessBuilder("bash", "-c", comando);

                pb.start();
            }
        }
    }
}
