# Manejo de Errores y Validaciones en Comentarios de Producción

El sistema de comentarios en línea de una plataforma financiera permite a los usuarios publicar sus opiniones sobre diferentes productos. El sistema debe manejar adecuadamente los errores y validaciones para asegurar la integridad y consistencia de los datos. Los comentarios deben ser únicos, no deben contener lenguaje ofensivo y deben estar asociados a un producto válido. El sistema debe registrar los comentarios con una clave de idempotencia para evitar duplicados y manejar adecuadamente los errores de red o del sistema.

## Informacion General

| Campo | Valor |
|-------|-------|
| **Tema** | E2E prod comments 1790905766 |
| **Nivel** | senior-l2 |
| **Tipo** | practical |
| **Tiempo estimado** | 8 horas |

## Fases del Reto

### Fase 0: Configuración del Proyecto

**Objetivo:** Obtener el proyecto base funcional enviando el Código Base a un asistente de IA, que lo analizará, corregirá errores y generará un ZIP listo para usar.

**Tiempo estimado:** 15-30 minutos

**Instrucciones:**

- Asegúrate de tener instalado para ejecutar el proyecto: JDK 17+, Maven 3.9+, IDE con soporte Java.
- Copia todo el contenido del campo **Código Base** de este reto — incluyendo el texto de instrucciones que aparece al inicio.
- Abre un asistente de IA (Claude en claude.ai, ChatGPT o Gemini — se recomienda Claude), pega el contenido copiado en el chat y envíalo.
- El asistente analizará los archivos, corregirá errores y generará un archivo ZIP descargable. Descárgalo y extráelo en la carpeta donde quieras trabajar.
- Ejecuta `mvn compile` en la raíz. Si no hay errores, estás listo.

**Entregable:** El proyecto compila/arranca sin errores.

<details>
<summary>Pistas de conocimiento</summary>

- Copia el Código Base completo incluyendo el texto de instrucciones al inicio — esas instrucciones le indican al asistente exactamente qué hacer con los archivos.
- Si el asistente no genera el ZIP automáticamente al terminar el análisis, escríbele: "genera el ZIP ahora".
- Si el proyecto tiene errores al arrancar, comparte el mensaje de error con el mismo asistente para que lo corrija.

</details>

### Fase 1: Implementación de Validaciones Básicas

**Objetivo:** Asegurar que los comentarios cumplan con las reglas de validación básicas antes de ser persistidos.

**Tiempo estimado:** 2 horas

**Instrucciones:**

- Implementar validaciones para asegurar que los comentarios no contengan lenguaje ofensivo.
- Validar que los comentarios sean únicos y estén asociados a un producto válido.

**Entregable:** Sistema que aplica validaciones básicas a los comentarios antes de persistirlos.

<details>
<summary>Pistas de conocimiento</summary>

- Considera el uso de listas negras para detectar lenguaje ofensivo.
- Piensa en cómo verificar la unicidad de los comentarios de manera eficiente.

</details>

### Fase 2: Manejo de Errores y Registro de Comentarios

**Objetivo:** Implementar un mecanismo para manejar errores y registrar comentarios con idempotencia.

**Tiempo estimado:** 3 horas

**Instrucciones:**

- Implementar un mecanismo para manejar errores de red o del sistema durante el registro de comentarios.
- Asegurar que los comentarios se registren con una clave de idempotencia para evitar duplicados.

**Entregable:** Sistema que maneja errores y registra comentarios con idempotencia.

<details>
<summary>Pistas de conocimiento</summary>

- Considera el uso de reintentos con backoff exponencial para manejar errores transitorios.
- Piensa en cómo generar y utilizar una clave de idempotencia para cada comentario.

</details>

### Fase 3: Optimización y Refactorización

**Objetivo:** Optimizar el rendimiento y refactorizar el código para mejorar su mantenibilidad.

**Tiempo estimado:** 3 horas

**Instrucciones:**

- Optimizar el rendimiento del sistema para manejar un mayor volumen de comentarios.
- Refactorizar el código para mejorar su legibilidad y mantenibilidad.

**Entregable:** Sistema optimizado y refactorizado para manejar un mayor volumen de comentarios.

<details>
<summary>Pistas de conocimiento</summary>

- Considera el uso de caché para mejorar el rendimiento de las validaciones.
- Piensa en cómo modularizar el código para mejorar su legibilidad y mantenibilidad.

</details>

## Dimensiones Evaluadas

- **queEs**: ¿Qué son las validaciones y el manejo de errores en el contexto de los comentarios de producción?
- **paraQueSirve**: ¿Para qué sirven las validaciones y el manejo de errores en el sistema de comentarios?
- **comoSeUsa**: ¿Cómo se implementan las validaciones y el manejo de errores en el sistema de comentarios?
- **erroresComunes**: ¿Cuáles son los errores comunes que pueden ocurrir durante el registro de comentarios y cómo se manejan?
- **queDecisionesImplica**: ¿Qué decisiones de diseño implica la implementación de validaciones y manejo de errores en el sistema de comentarios?

## Criterios de Evaluacion

- Implementación de validaciones básicas para los comentarios.
- Manejo adecuado de errores durante el registro de comentarios.
- Uso de idempotencia para evitar duplicados en los comentarios.
- Optimización del rendimiento del sistema para manejar un mayor volumen de comentarios.
- Refactorización del código para mejorar su legibilidad y mantenibilidad.

## Como trabajar con un asistente de IA

Hay dos caminos, elegi uno:

- **AGENTS.md** (recomendado) — instrucciones nativas del repo. Abri esta carpeta con tu agente local (Claude Code, Cursor, Codex, Copilot, Gemini) y las carga solo. Sabe que archivos faltan y con que comando se verifica, y completa el scaffold escribiendo en disco.
- **PROMPT_MEJORA.md** — para copiar y pegar en un chat (claude.ai, ChatGPT). Devuelve un ZIP con el proyecto. Sirve si no tenes un agente en el IDE.

Ninguno de los dos resuelve las fases del reto: eso es tu trabajo.

## Verificacion

El proyecto esta listo para trabajar cuando este comando corre sin errores:

```bash
mvn clean compile
```

---

*Reto generado automaticamente por Challenge Generator - Pragma*
