enum class Nivel { BASICO, INTERMEDIARIO, DIFICIL }

open class Usuario(val id: Int, val nome: String)

class Aluno(id: Int, nome: String, val xp: Int = 0) : Usuario(id, nome)

class ConteudoEducacional(val nome: String, val duracaoMin: Int = 60)

class Formacao(val nome: String, val nivel: Nivel, vararg conteudos: ConteudoEducacional) {
    
    private val inscritos: MutableList<Aluno> = mutableListOf()
    
    private val conteudos: Array<out ConteudoEducacional> = conteudos
    
    fun getConteudos(): Array<out ConteudoEducacional>{
        return conteudos
    }
    
    fun getTotalTime(): Int {
        var total: Int = 0
        for (conteudo in conteudos) {
            total += conteudo.duracaoMin
        }
        return total
    }
    
    fun matricular(aluno: Aluno): Boolean {
        return inscritos.add(aluno)
    }
    
    fun desmatricular(aluno: Aluno): Boolean {
        return inscritos.remove(aluno)
    }
    
    fun getAlunos() : MutableList<Aluno> {
        return inscritos
    }
}

class Testes {
    
    
    init{
		
        var error : Int = 0;
        if (criarAluno()) println("Teste criarAluno passou") else error++
        if (criarConteudoEducacional()) println("Teste criarConteudoEducacional passou") else error++            
		if (criarFormacao()) println("Teste criarFormacao passou") else error++
        if (matricularEdesmatricularAluno()) println("Teste matricularAluno passou") else error++
        
        println(if (error>0) "Testes não passaram. Quantidade = ${error} " else "Todos os testes passaram")
    }
    
    private fun criarAluno(): Boolean {
        return try {
            val aluno = Aluno(0, "nome teste")
            when ( aluno.id == 0 && aluno.nome == "nome teste"){
                true -> true
        		else -> throw IllegalArgumentException("O valor não está sendo iniciado corretamente na classe aluno")
            }
        } catch (e: Exception) {
            println("Erro: ${e.message}")
            false
        }
    }
    
    private fun criarConteudoEducacional() : Boolean{
        return try {
            val conteudo = ConteudoEducacional("Teste em kotlin", 120)
			when ( conteudo.nome == "Teste em kotlin" && conteudo.duracaoMin == 120){
                true -> true
        		else -> throw IllegalArgumentException("O valor não está sendo iniciado corretamente na classe conteudo")
            }
        }catch(e : Exception){
			println("Erro: ${e.message}")
            false
        }
    }
    
    
    private fun criarFormacao() : Boolean{
        return try {
			val formacao = createFormacao()
            when(
            	formacao.nome == "Formação em kotlin" &&
                formacao.nivel == Nivel.BASICO &&
                formacao.getConteudos()[0].nome == "Teste em kotlin" && 
                formacao.getConteudos()[1].nome == "Teste em kotlin1" &&
                formacao.getConteudos()[2].nome == "Teste em kotlin2" &&
                formacao.getConteudos()[3].nome == "Teste em kotlin3" &&
				formacao.getAlunos().count() == 0 &&
                formacao.getTotalTime() == 400
            ){
                true -> true
        		else -> throw IllegalArgumentException("O valor não está sendo iniciado corretamente na classe Formacao")
            }
        }catch(e : Exception){
			println("Erro: ${e.message}")
            false
        }
    }
        
    private fun matricularEdesmatricularAluno() : Boolean{
        return try {
            val aluno = Aluno(1,"Nome",50)
			val formacao = createFormacao()
            formacao.matricular(aluno)
			val alunos = formacao.getAlunos()
			when(
                 alunos.count() == 1 &&
            	 alunos.first().id == 1 &&
                 alunos.first().xp == 50
            ){
                true -> (formacao.desmatricular(aluno) and (alunos.count() == 0))
        		else -> throw IllegalArgumentException("Não foi possivel matricular aluno")
            }
		}catch(e : Exception){
			println("Erro: ${e.message}")
            false
        }
    }
    
    private fun createFormacao() : Formacao { 
    		val conteudo = ConteudoEducacional("Teste em kotlin", 100)
			val conteudo1 = ConteudoEducacional("Teste em kotlin1", 120)
			val conteudo2 = ConteudoEducacional("Teste em kotlin2", 120)
			val conteudo3 = ConteudoEducacional("Teste em kotlin3", 60)
			val formacao = Formacao("Formação em kotlin", Nivel.BASICO, conteudo, conteudo1, conteudo2, conteudo3)
    		return formacao
    }
}

fun main() {
    Testes()
}
