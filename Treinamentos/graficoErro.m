function graficoErro(passo, funcao, arq)
  switch funcao
    case 1
      funcao = "um";
    case 2
      funcao = "dois";
  end

  switch passo
    case 1
      passo = " 1";
    case 2
      passo = " 2";
    case 3
      passo = " 3";
    case 4
      passo = " 4";
  end
  
  switch arq
    case 1
      arq = "um";
    case 2
      arq = "dois";
    case 3
      arq = "tres";
  end

  y_treino = load(strcat("Passo ", passo, "/funcao_", funcao, "_arq_", arq, "_erro_treino.erro"));
  y_val = load(strcat("Passo ", passo, "/funcao_", funcao, "_arq_", arq, "_erro_val.erro"));

  x = 1:1:size(y_treino)(1);

  hold
  plot(x, y_treino, 'r');
  plot(x, y_val, 'b');
end
