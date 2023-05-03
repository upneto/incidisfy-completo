
const URL_BASE = 'https://incidisfy-backend-gateway.herokuapp.com';

export const environment = {
  production: true,
  base: URL_BASE,
  api: {
    login: URL_BASE + '/login',
    ordemServico: URL_BASE + '/api/ordem_servico',
    veiculo: URL_BASE + '/api/veiculo',
    cliente: URL_BASE + '/api/cliente'
  }
}
