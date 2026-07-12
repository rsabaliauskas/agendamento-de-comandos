import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class LerComandos {
    public static void main(String[] args) throws InterruptedException {

        /* Arquivo run.txt
        *  Usado para interromper o loop de pesquisa de data e comando a ser executado
        *  Sem necessidade de matar o processo por comandos
        */
        Path run = Path.of("run.txt");

        try {
            if (!Files.exists(run)) {
                /* Cria
                *  Escreve "0" para tornar o loop ativo
                */ Se escrever "1" no lugar de "0" o loop será interrompido

                Files.createFile(run);
                Files.writeString(run, "0" + System.lineSeparator());
                System.out.println("[Aviso]: Arquivo run.txt criado com sucesso com caracter \"0\" escrito nele!");
            } else {
                Files.deleteIfExists(run);
                Files.createFile(run);
                Files.writeString(run, "0" + System.lineSeparator());
                System.out.println("[Aviso]: Arquivo run.txt foi recriado com caracter \"0\" escrito nele!.");
            }
        } catch (IOException e) {
            System.out.println("[ERRO]: Ao criar/recriar o arquivo run.txt: " + e.getMessage());
        }

        String filesRun = "0";

        // Loop  while que para procurar uma correspondência da data/hora atual e executar o comando

        while (filesRun.equals("0")) {
            //System.out.println("[Aviso]: Status OK! Verificando comandos...");

            try {
                // Chama a classe e método do outro arquivo
                ExecutarComandos.executar();
            } catch (IOException e) {
                System.out.println("[ERRO]: Falha ao processar a classe ExecutarComandos: " + e.getMessage());
            }

            // Atualiza a variável "filesRun" pra continuar ou não o loop
            try {
                if (Files.exists(run)) {
                    filesRun = Files.readString(run).trim();
                } else {
                    filesRun = "1";
                }
            } catch (IOException e) {
                filesRun = "1";
            }

            // Espera minuto para o loop continuar/encerrar
            if (filesRun.equals("0")) {
                Thread.sleep(60000);
            }
        }
        System.out.println("[Aviso]: Loop finalizado pelo arquivo run.txt com sucesso.");
    }
}
