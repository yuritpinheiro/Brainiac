function graficoFuncao(funcao, arq)

  x = 0:.1:15;

  switch arq
    case 1
      arq = "um";
    case 2
      arq = "dois";
    case 3
      arq = "tres";
  end
  
  switch funcao
    case 1
      funcao = "um";
      y_funcao_real = 2/5 + exp(-5 * x)/10 - exp(-x)/2;
      y_funcao_aprox = load(strcat("funcao_um_arq_", arq, "_aprox.funcao"));
    case 2
      funcao = "dois";
      for (i = 1:1:151)
        y_funcao_real(i) = x(i) * exp(-2 * x(i));
      end
      y_funcao_aprox = load(strcat("funcao_dois_arq_", arq, "_aprox.funcao"));
  end
  
  y_treino = load(strcat("funcao_", funcao, "_arq_", arq, "_erro_treino.erro"));
  y_val = load(strcat("funcao_", funcao, "_arq_", arq, "_erro_val.erro"));

  hold
  plot(x, y_funcao_real, 'k');
  plot(x, y_funcao_aprox, 'xr');
end