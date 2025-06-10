# Sistema de Análise de Pontos 2D

Um sistema Java para análise e classificação de pontos em um plano cartesiano, permitindo determinar quadrantes, calcular distâncias e gerar estatísticas detalhadas.

## 📋 Funcionalidades

- **Classificação de pontos** por quadrantes (1°, 2°, 3°, 4°)
- **Detecção de pontos especiais** (origem, sobre eixos)
- **Cálculo de distâncias** entre pontos
- **Estatísticas automáticas** (ponto mais próximo/distante, distribuição por quadrantes)
- **Tratamento robusto de erros** com mensagens em português
- **Validação de entrada** para garantir dados válidos

## 🏗️ Arquitetura do Sistema

### Classes Principais

#### `Point.java`
**Responsabilidade**: Representação de um ponto 2D com operações matemáticas.

**Principais métodos:**
- `distanceTo(Point other)` - Calcula distância euclidiana
- `isAtOrigin()` - Verifica se está na origem (0,0)
- `isOnAxis(Point origin)` - Verifica se está sobre um eixo
- `getQuadrant(Point origin)` - Determina o quadrante (1-4, ou 0 se sobre eixo)

**Características:**
- Imutável (campos `final`)
- Implementa `equals()` e `toString()`
- Coordenadas armazenadas como `double`

#### `PointAnalyzer.java`
**Responsabilidade**: Análise e classificação de pontos, mantendo estatísticas.

**Principais funcionalidades:**
- Análise individual de pontos com `analyzeAndClassifyPoint()`
- Rastreamento automático de extremos (mais próximo/distante)
- Contagem por quadrantes
- Validação de dados de entrada

**Estatísticas mantidas:**
- Ponto mais próximo e mais distante da origem
- Distâncias mínima e máxima
- Distribuição de pontos por quadrante
- Contador de pontos válidos processados

#### `InputHandler.java`
**Responsabilidade**: Gerenciamento de entrada do usuário com validação.

**Métodos principais:**
- `getOrigin()` - Solicita coordenadas da origem
- `getNumberOfPoints()` - Solicita quantidade de pontos
- `getPoint(int index)` - Solicita coordenadas de um ponto específico

**Validações implementadas:**
- Verificação de tipos numéricos
- Validação de números finitos
- Limpeza de buffer em caso de erro
- Verificação de valores positivos (quantidade de pontos)

#### `ResultsDisplay.java`
**Responsabilidade**: Formatação e exibição de resultados e erros.

**Tipos de exibição:**
- Erros com contexto (inicialização, processamento, exibição)
- Erros específicos por ponto
- Resultados finais com estatísticas
- Avisos para casos sem pontos válidos

**Formato de saída:**
- Mensagens em português
- Formatação numérica consistente (1 casa decimal para coordenadas, 2 para distâncias)
- Organização clara dos resultados

### Classes de Exceção

#### `AnalysisException.java`
**Uso**: Erros durante análise de pontos (cálculos inválidos, pontos nulos).

#### `InvalidInputException.java`
**Uso**: Erros de entrada do usuário (tipos incorretos, valores inválidos).

**Características das exceções:**
- Herdam de `Exception` (checked exceptions)
- Suportam mensagens personalizadas
- Permitem encadeamento de causas

#### `Main.java`
**Responsabilidade**: Ponto de entrada da aplicação com coordenação do fluxo.

**Estrutura do fluxo:**
1. **Inicialização**: Coleta origem e quantidade de pontos
2. **Processamento**: Loop de entrada e análise individual
3. **Resultados**: Exibição de estatísticas finais
4. **Limpeza**: Fechamento de recursos

**Tratamento de erros por bloco:**
- Erros de inicialização: Encerra programa
- Erros de ponto individual: Ignora ponto, continua processamento
- Erros de exibição: Tenta continuar
- Erros de limpeza: Registra mas não interrompe

## 🚀 Como Usar

### Compilação
```bash
javac *.java
```

### Execução
```bash
java Main
```

### Exemplo de Uso
```
Digite a origem do eixo x: 0
Digite a origem do eixo y: 0
Digite o número de pontos: 3

Ponto 1:
Digite o x do ponto: 2.5
Digite o y do ponto: 3.0
O ponto (2.5, 3.0) está no 1° quadrante

Ponto 2:
Digite o x do ponto: -1.0
Digite o y do ponto: 4.0
O ponto (-1.0, 4.0) está no 2° quadrante

Ponto 3:
Digite o x do ponto: 0
Digite o y do ponto: 5.0
O ponto (0.0, 5.0) está sobre o eixo das coordenadas

=== RESULTADOS FINAIS ===
Total de pontos válidos processados: 3
Ponto mais próximo: (2.5, 3.0) (distância: 3.91)
Ponto mais distante: (0.0, 5.0) (distância: 5.00)

Distribuição por quadrantes:
  1° quadrante: 1 ponto(s)
  2° quadrante: 1 ponto(s)
  3° quadrante: 0 ponto(s)
  4° quadrante: 0 ponto(s)
```

## 🛡️ Tratamento de Erros

### Tipos de Erro Tratados

**Erros de Entrada:**
- Texto onde esperava número
- Números infinitos ou NaN
- Quantidade negativa ou zero de pontos

**Erros de Processamento:**
- Pontos nulos
- Cálculos que resultam em valores inválidos
- Erros inesperados durante análise

**Comportamento em Erros:**
- **Erros críticos**: Encerram o programa com mensagem explicativa
- **Erros de ponto**: Ignoram o ponto específico, continuam processamento
- **Sem pontos válidos**: Exibem aviso, não crasham

## 📐 Detalhes Técnicos

### Sistema de Quadrantes
```
    2° | 1°
  -----+-----
    3° | 4°
```

### Cálculo de Distância
Utiliza a fórmula euclidiana: `√[(x₂-x₁)² + (y₂-y₁)²]`

### Validações
- **Números finitos**: Rejeita `Infinity`, `-Infinity`, `NaN`
- **Tipos corretos**: `double` para coordenadas, `int` para quantidade
- **Valores lógicos**: Quantidade de pontos > 0

## 🔧 Requisitos

- **Java 8+** (usa `Double.isFinite()`)
- **JDK** para compilação
- **Terminal/Console** para interação

## 📝 Padrões de Código

- **Imutabilidade**: Classe `Point` é imutável
- **Encapsulamento**: Campos privados com getters apropriados
- **Separação de responsabilidades**: Cada classe tem função específica
- **Tratamento defensivo**: Validações em todos os pontos de entrada
- **Mensagens localizadas**: Todas as mensagens em português