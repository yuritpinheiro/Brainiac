function graficoFuncao(passo, funcao, arq)

  x = 0:.1:15;

  switch arq
    case 1
      arq = "um";
    case 2
      arq = "dois";
    case 3
      arq = "tres";
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
  
  switch funcao
    case 1
      funcao = "um";
      y_funcao_real = 2/5 + exp(-5 * x)/10 - exp(-x)/2;
      y_funcao_aprox = load(strcat("Passo ", passo, "/funcao_um_arq_", arq, "_aprox.funcao"));

    case 2
      funcao = "dois";
      for (i = 1:1:151)
        y_funcao_real(i) = x(i) * exp(-2 * x(i));
      end
      y_funcao_aprox = load(strcat("Passo ", passo, "/funcao_dois_arq_", arq, "_aprox.funcao"));
  end
  
  hold
  plot(x, y_funcao_real, 'k');
  plot(x, y_funcao_aprox, 'xr');
end
