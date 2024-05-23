enum class Nivel { BASICO, INTERMEDIARIO, DIFICIL }

open class Usuario(val id: Int, val nome: String)

class Aluno(val xp : Int, id: Int, nome: String): Usuario(id,nome)

class ConteudoEducacional(val nome: String, val duracao: Int = 60)

class Formacao(val nome: String, val nivel: Nivel, vararg conteudos: ConteudoEducacional) {
    
    private val inscritos: MutableList<Aluno> = mutableListOf()
        
    fun getTotalTime(): Int{
        val total: Int = 0
        for (conteudo in this.conteudos) {
            total += conteudo.duracao
        }
        return total
    }
    
    fun matricular(aluno:Aluno): Boolean {
       	return inscritos.add(aluno)
    }
    
    fun desmatricular(aluno:Aluno): Boolean{
        return inscritos.remove(aluno)
    }
}

class testes

fun main() {
    //
}

