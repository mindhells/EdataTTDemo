/*
Dada la estructura de datos Archivador, Cajón, Documento, Anexo. Donde un Archivador
tiene uno o más Cajones, un Cajón tiene de cero a muchos Documentos y un Documento
tiene de 0 a muchos Anexos, realizar las siguientes queries:
*/


-- Ejercicio 1: Obtener la posición de todos los documentos archivados durante 2014


SELECT
DO.ID, DO.TITULO, AR.NUMERO, CA.POSICION
FROM DOCUMENTO DO
LEFT OUTER JOIN CAJON CA ON CA.ID = DO.CAJON_ID
LEFT OUTER JOIN ARCHIVO AR ON AR.ID = CA.ARCHIVO_ID
WHERE EXTRACT(YEAR FROM DO.FECHA_ARCHIVADO) = 2014;



-- Ejercicio 2: Obtener la posición de todos los documentos archivados en 2014 añadiendo 
-- el título de sus anexos de tipo factura en caso de existir


SELECT
DO.ID, DO.TITULO, AN.TITULO, AR.NUMERO, CA.POSICION
FROM DOCUMENTO DO
LEFT OUTER JOIN ANEXO AN ON AN.DOCUMENTO_ID = DO.ID AND AN.TIPO_ANEXO = 'factura'
LEFT OUTER JOIN CAJON CA ON CA.ID = DO.CAJON_ID
LEFT OUTER JOIN ARCHIVO AR ON AR.ID = CA.ARCHIVO_ID
WHERE EXTRACT(YEAR FROM DO.FECHA_ARCHIVADO) = 2014;



-- Ejercicio 3: Contar el número de documentos que tiene al menos un anexo de tipo factura


SELECT
COUNT(DISTINCT AN.DOCUMENTO_ID)
FROM ANEXO AN
WHERE AN.TIPO_ANEXO = 'factura';



-- Ejercicio 4: Obtener la posición de todos los documentos y su último anexo de tipo factura, si éste existe.


SELECT
DO.ID, DO.TITULO, AN.TITULO, AR.NUMERO, CA.POSICION
FROM DOCUMENTO DO
LEFT OUTER JOIN (
  SELECT ID, DOCUMENTO_ID, TITULO, ROW_NUMBER()
  OVER (PARTITION BY DOCUMENTO_ID ORDER BY FECHA_ARCHIVADO DESC) AS ANPOS
  FROM ANEXO WHERE TIPO_ANEXO = 'factura'
) AN ON AN.DOCUMENTO_ID = DO.ID AND AN.ANPOS = 1
LEFT OUTER JOIN CAJON CA ON CA.ID = DO.CAJON_ID
LEFT OUTER JOIN ARCHIVO AR ON AR.ID = CA.ARCHIVO_ID;
