package br.com.j.hemopa.digital.util;

import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Locale;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.Months;

import br.com.j.hemopa.digital.dominios.DominioMes;

public class DataUtil {
	
	public static Date getDataAtual() {

		return new Date();
	}

	public static Date getDataAtualTruncada() {

		return DataUtil.truncate(DataUtil.getDataAtual(), Calendar.HOUR_OF_DAY);
	}

	public static Date getPrimeiroDiaDo(Integer ano) {

		return DataUtil.toDate(StringUtils.join("01/01/", ano.toString()));
	}

	public static String getUltimoDiaDo(Integer ano) {

		return StringUtils.join("31/12/", ano.toString());

	}

	public static Date getDataVencimentoMesAtual() {

		return DateUtils.setDays(DataUtil.truncate(DataUtil.getDataAtual(), Calendar.HOUR_OF_DAY), 10);
	}

	@SuppressWarnings("unchecked")
	public static Date getDataVencimentoCompetenciaPara(DominioMes mes, Integer ano) {

		return DataUtil.adicionarMes(DataUtil.getDataCompetenciaPara(mes, ano), 1);
	}

	public static Date getDataCompetenciaPara(DominioMes mes, Integer ano) {

		return DataUtil.truncate(DataUtil.toDate(StringUtils.join(01, "/", mes.getNumero(), "/", ano)), Calendar.HOUR_OF_DAY);
	}

	public static Date getUtimaDataUtilDoMes() {

		return DateUtils.setDays(DataUtil.getDataAtual(), DateUtils.toCalendar(DataUtil.getDataAtual()).getActualMaximum(Calendar.DAY_OF_MONTH));
	}

	public static Date getUtimaDataUtilDoProximoMes(final Date data) {

		return DateUtils.setDays(DataUtil.adicionarMes(data),
						DateUtils.toCalendar(DataUtil.adicionarMes(data)).getActualMaximum(Calendar.DAY_OF_MONTH));
	}

	public static String getUltimoDiaDoAnoCorrente() {

		return StringUtils.join("31/12/", DataUtil.getAnoAtual());

	}

	public static int getMesAtual() {

		return DataUtil.getDataAtual().getMonth() + 1;
	}

	public static DominioMes getMesAtualEnum() {

		return DominioMes.mesAtual(DataUtil.getDataAtual().getMonth());
	}

	public static DominioMes getMesAtual(Date data) {

		return DominioMes.mesAtual(data.getMonth());
	}

	public static boolean isMesAtual(Date data) {

		return DataUtil.getDataAtual().getMonth() == data.getMonth();
	}

	// public static boolean isVenceNoMesAtual(Date data) {
	//
	// return DataUtil.truncate(DataUtil.getDataAtual(), Calendar.HOUR_OF_DAY);
	// }

	public static boolean beforeTruncate(Date data) {

		return DataUtil.beforeTruncate(data, DataUtil.getDataAtual());
	}

	public static boolean before(Date data) {

		return DataUtil.before(data, DataUtil.getDataAtual());
	}

	public static boolean beforeTruncate(Date data, Date dataLimite) {

		return (data != null) && (dataLimite != null)
						&& DataUtil.before(DataUtil.truncate(data, Calendar.HOUR_OF_DAY), DataUtil.truncate(dataLimite, Calendar.HOUR_OF_DAY));
	}

	public static boolean before(Date data, Date dataLimite) {

		return (data != null) && (dataLimite != null) && data.before(dataLimite);
	}

	public static boolean afterTruncate(Date data) {

		return DataUtil.afterTruncate(data, DataUtil.getDataAtual());
	}

	public static boolean after(Date data) {

		return DataUtil.after(data, DataUtil.getDataAtual());
	}

	public static boolean afterTruncate(Date data, Date dataLimite) {

		return (data != null) && (dataLimite != null)
						&& DataUtil.after(DataUtil.truncate(data, Calendar.HOUR_OF_DAY), DataUtil.truncate(dataLimite, Calendar.HOUR_OF_DAY));
	}

	public static boolean after(Date data, Date dataLimite) {

		return (data != null) && (dataLimite != null) && data.after(dataLimite);
	}

	/**
	 * Trunca a data informada retornando um novo objeto {@link Date}.
	 *
	 * @param date
	 *            data a ser truncada.
	 * @param level
	 *            parametro base a ser truncado Exemplo: com data base 30/03/2010
	 *            15:27:32.567)
	 *            <ul>
	 *            <li>Calendar.YEAR: trunca a data a partir do ano (01/jan/0001
	 *            00:00:00.000).</li>
	 *            <li>Calendar.MONTH: trunca a data a partir do mes (01/jan/2010
	 *            00:00:00.000).</li>
	 *            <li>Calendar.DAY_OF_MONTH trunca adata a partir do dia
	 *            (01/mar/2010 00:00:00.000).</li>
	 *            <li>Calendar.HOUR: trunca a data a partir da hora (30/mar/2010
	 *            00:00:00.000).</li>
	 *            <li>Calendar.MINUTE: trunca a data a partir do minuto
	 *            (30/mar/2010 15:00:00.000).</li>
	 *            <li>Calendar.SECOND: trunca a data a partir do segundo
	 *            (30/mar/2010 15:27:00.000).</li>
	 *            <li>Calendar.MILLISECOND: trunca a data a partir do milisegundo
	 *            (30/mar/2010 15:27:32.000).</li>
	 *            </ul>
	 *
	 * @return nova data truncada a partir do nivel informado.
	 */
	public static Date truncate(Date date, int level) {

		if (date == null) {
			throw new IllegalArgumentException("invalid date");
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		switch (level) {
			case Calendar.YEAR:
				cal.set(Calendar.YEAR, cal.getActualMinimum(Calendar.YEAR));
			case Calendar.MONTH:
				cal.set(Calendar.MONTH, cal.getActualMinimum(Calendar.MONTH));
			case Calendar.DAY_OF_MONTH:
				cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DAY_OF_MONTH));
			case Calendar.HOUR_OF_DAY:
				cal.set(Calendar.HOUR_OF_DAY, cal.getActualMinimum(Calendar.HOUR_OF_DAY));
			case Calendar.MINUTE:
				cal.set(Calendar.MINUTE, cal.getActualMinimum(Calendar.MINUTE));
			case Calendar.SECOND:
				cal.set(Calendar.SECOND, cal.getActualMinimum(Calendar.SECOND));
			case Calendar.MILLISECOND:
				cal.set(Calendar.MILLISECOND, cal.getActualMinimum(Calendar.MILLISECOND));
				break;
			default:
				throw new IllegalArgumentException("invalid level");
		}
		return cal.getTime();
	}

	public static Date truncate(Date date) {

		return DataUtil.truncate(date, Calendar.HOUR_OF_DAY);
	}

	public static String getDataComoString(Date data) {

		return StringUtils.join(new SimpleDateFormat("dd", new Locale("pt", "BR")).format(data), " de ",
						new SimpleDateFormat("MMMM", new Locale("pt", "BR")).format(data), " de ",
						new SimpleDateFormat("yyyy", new Locale("pt", "BR")).format(data));
	}

	public static Date adicionarMes(final Date data) {

		return DateUtils.setDays(DataUtil.adicionarMes(data, 1), 10);
	}

	public static Date adicionarMes(final Date data, final int quantidade) {

		return DateUtils.addMonths(data, quantidade);
	}

	public static Date adicionarMeses(final Date data, final int quantidade) {

		return DateUtils.setDays(DataUtil.adicionarMes(data, quantidade), 10);
	}

	public static Date adicionarMeses(final Date data, final int dia, final int quantidade) {

		return DateUtils.setDays(DateUtils.addMonths(data, quantidade), dia);
	}

	public static Date adicionarDia(final Date data) {

		return DateUtils.addDays(data, 1);
	}

	public static Date adicionarDias(final Date data, int dias) {

		return DateUtils.addDays(data, dias);
	}

	public static String competencia(final Date data) {

		return new SimpleDateFormat("MM/yyyy").format(data);
	}

	public static String competencia(final DominioMes mes, final Integer ano) {

		return StringUtils.join(new String[] { mes.getCodigo().toString(), ano.toString() }, "/");
	}

	public static String toString(final Date data) {

		return DataUtil.toString(data, "dd/MM/yyyy");
	}

	public static String formatoAmericandoSemBarra(final Date data) {

		return DataUtil.toString(data, "yyyyMMdd");
	}

	public static Date toDate(final String data) {

		try {
			return DateUtils.parseDate(data, "dd/MM/yyyy");
		} catch (ParseException e) {
			return null;
		}
	}

	public static Date toDateWithHours(final String data) {

		try {
			return DateUtils.parseDate(data, "dd/MM/yyyy HH:mm:ss");
		} catch (ParseException e) {
			return null;
		}
	}

	public static Date toDate(LocalDateTime localDateTime) {

		return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
	}

	public static String hash() {

		return DataUtil.toString(DataUtil.getDataAtual(), "ddMMyyyyHHmmss");
	}

	public static Integer getAnoAtual() {

		return Integer.parseInt(DataUtil.toString(DataUtil.getDataAtual(), "yyyy"));
	}

	public static Integer getAnoPosterior() {

		return Integer.parseInt(DataUtil.toString(DataUtil.getDataAtual(), "yyyy")) + 1;
	}

	public static Integer getAnoAnterior(Date competencia) {

		return DataUtil.getAno(competencia) - 1;
	}

	public static Integer getAnoAnterior() {

		return Integer.parseInt(DataUtil.toString(DataUtil.getDataAtual(), "yyyy")) - 1;
	}

	public static Integer getAno(Date data) {

		return Integer.parseInt(DataUtil.toString(data, "yyyy"));
	}

	public static boolean isAnoAtual(Date data) {

		return DataUtil.getAnoAtual().toString().equals(DataUtil.getAno(data).toString());
	}

	public static boolean isAnoMaiorIgualAtual(Date data) {

		return DataUtil.getAno(data).compareTo(DataUtil.getAnoAtual()) >= 0;
	}

	public static String toString(final Date data, final String formato) {

		return new SimpleDateFormat(formato).format(data);
	}

	public static boolean isFinalDeSemana(final Date data) {

		final Calendar calendar = DateUtils.toCalendar(data);
		return (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) || (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY);
	}

	/*
	 * public static int getQuantidadeMesesVencidos(final Date dataVencimento, final
	 * Date dataCalculo) { return Months.monthsBetween(new
	 * DateTime(DateUtils.setDays(dataVencimento, 1)), new
	 * DateTime(dataCalculo)).getMonths() + 1; } public static int
	 * getQuantidadeDiasVencidos(final Date dataVencimento, final Date dataCalculo)
	 * { return Days.daysBetween(new DateTime(DateUtils.setDays(dataVencimento, 1)),
	 * new DateTime(dataCalculo)).getDays(); }
	 */

	// FIXME: teste
	public static void main(String[] args) {
		Date dataProcesso = toDate("17/09/2019");
		Date dataLimite = toDate("18/09/2019");
		//se dataProcesso anterior a dataLimite é verdadeiro = true
		boolean afterTruncate = beforeTruncate(dataProcesso, dataLimite);

		System.out.println("Resultado: " + afterTruncate);

	}

	/**
	 *
	 * @param dataUm
	 * @param dataDois
	 * @return hashMap com a soma entre as duas datas passadas por parâmetro,
	 *         contendo as seguintes keys: hora, minuto, segundo
	 */
	public static HashMap<String, Long> somarHorasDates(Date dataUm, Date dataDois) {

		HashMap<String, Long> somas = new HashMap<>();

		LocalDateTime d1 = converterParaLocalDateTime(dataUm);
		LocalDateTime d2 = converterParaLocalDateTime(dataDois);

		Duration duration = Duration.between(d1, d2);
		long segundos = Math.abs(duration.getSeconds());
		long horas = segundos / 3600;
		segundos -= (horas * 3600);
		long minutos = segundos / 60;
		segundos -= (minutos * 60);
		somas.put("hora", horas);
		somas.put("minuto", minutos);
		somas.put("segundo", segundos);

		return somas;
	}

	public static LocalDateTime converterParaLocalDateTime(Date dateParaConversao) {

		return dateParaConversao.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
	}

	public static LocalDate converterParaLocalDate(Date dateParaConversao) {

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

		try {
			Date data = sdf.parse(sdf.format(dateParaConversao));
			return data.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return dateParaConversao.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	}

	public static LocalTime converterParaLocalTime(Time dateParaConversao) {

		return dateParaConversao.toInstant().atZone(ZoneId.systemDefault()).toLocalTime();
	}

	public static String diferencaEntreDatasEmHoras(Date dataInicio, Date dataFim) {

		try {

			long diff = dataFim.getTime() - dataInicio.getTime();
			long diffSeconds = (diff / 1000) % 60;
			long diffMinutes = (diff / (60 * 1000)) % 60;
			long diffHours = (diff / (60 * 60 * 1000)) % 24;
			long diffDays = diff / (24 * 60 * 60 * 1000);
			long diasEmHoras = (diffDays * 24) + diffHours;

			return diasEmHoras + "h:" + diffMinutes + "m:" + diffSeconds + "s";

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static Date converteStringParaDate(String data) throws Exception {

		if ((data == null) || data.equals("")) {
			return null;
		}
		Date date = null;
		try {

			DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			date = formatter.parse(data);
		} catch (ParseException e) {
			throw e;
		}
		return date;
	}

	public static Date converteStringParaDateUtil(String data) {

		DateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		try {
			return (StringUtils.isNotEmpty(data)) ? formatter.parse(data) : null;
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static Date getUtimaDataDoMes(final Date data) {

		return DateUtils.setDays(data, DateUtils.toCalendar(data).getActualMaximum(Calendar.DAY_OF_MONTH));
	}

	public static String getDataHoraTerminal() {

		return DataUtil.toString(DataUtil.getDataAtual(), "dd/MM/yyyy HH:mm:ss.S");
	}

	public static int getQuantidadeMesesVencidos(final Date dataVencimento, final Date dataCalculo) {

		return Months.monthsBetween(new DateTime(DateUtils.setDays(dataVencimento, 1)), new DateTime(dataCalculo)).getMonths() + 1;
	}

	public static int getQuantidadeDiasVencidos(final Date dataVencimento, final Date dataCalculo) {

		return Days.daysBetween(new DateTime(DateUtils.setDays(dataVencimento, 1)), new DateTime(dataCalculo)).getDays();
	}

	public static int getQuantidadeMesesAbsolutoVencidos(final Date dataVencimento, final Date dataCalculo) {

		final Date ultimoDiaMesDataVencimento = DataUtil.getUtimaDataDoMes(dataVencimento);

		int somaMes = DateUtils.isSameDay(DateUtils.toCalendar(ultimoDiaMesDataVencimento), DateUtils.toCalendar(dataVencimento)) ? 0 : 1;

		return Months.monthsBetween(new DateTime(DateUtils.setDays(dataVencimento, 1)), new DateTime(dataCalculo)).getMonths() + somaMes;
	}

	public static int getQuantidadeDiasAbsolutoVencidos(final Date dataVencimento, final Date dataCalculo) {

		return Days.daysBetween(new DateTime(dataVencimento), new DateTime(dataCalculo)).getDays();
	}

	public static boolean isUltimoMesDoAnoAtual() {

		return DataUtil.getMesAtual(DataUtil.getDataAtual())
						.compareTo(DataUtil.getMesAtual(DataUtil.toDate(DataUtil.getUltimoDiaDoAnoCorrente()))) == 0;
	}

	public static Date removerDias(final Date data, int dias) {

		return DateUtils.addDays(data, -1 * dias);
	}

	public static Date removeDia(final Date data) {

		return DateUtils.addDays(data, -1);
	}

	public static Integer dateParaInteiroFormatoAmericano(Date data) {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Integer dataInteiro = (Integer.parseInt(sdf.format(data)));
		return dataInteiro;
	}

	public static int diasEntreDatas(Date pDataInicio, Date pDataFim) {

		GregorianCalendar ini = new GregorianCalendar();
		GregorianCalendar fim = new GregorianCalendar();
		ini.setTime(pDataInicio);
		fim.setTime(pDataFim);
		long dt1 = ini.getTimeInMillis();
		long dt2 = fim.getTimeInMillis();
		return (int) (((dt2 - dt1) / 86400000L) + 1) - 1;
	}

	public static Calendar toCalendar(String strDate) throws ParseException {

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date date = sdf.parse(strDate);
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);

		return cal;
	}

	public static Date getHorarioAtual() {

		return Calendar.getInstance().getTime();
	}

	public static String getHorarioAtualFormatado() {

		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		String dataFormatada = sdf.format(DataUtil.getHorarioAtual());

		return dataFormatada;
	}

	public static Integer getDia(Date data) {

		return Integer.parseInt(getDiaComoString(data));
	}

	public static String getDiaComoString(Date data) {

		return new SimpleDateFormat("dd").format(data);
	}

	public static boolean isMesDezembro(Date dataLimite) {

		return DataUtil.getMesAtual(dataLimite).equals(DominioMes.DEZEMBRO);
	}

	public static boolean isDia31(Date dataLimite) {

		return (DataUtil.getDia(dataLimite).compareTo(new Integer(31)) == 0);
	}

	public static boolean isDia30(Date dataLimite) {

		return (DataUtil.getDia(dataLimite).compareTo(new Integer(30)) == 0);
	}

	public static LocalDateTime somarHoras(long horas) {

		LocalDateTime ldt = LocalDateTime.now().plusHours(horas);
		return ldt;
	}

	public static boolean isAnoMaiorIgual(Date dataVencimento, Date dataCalculo) {

		return DataUtil.getAno(dataVencimento).compareTo(DataUtil.getAno(dataCalculo)) >= 0;
	}

	public static DominioMes getMes(Date data) {

		return DominioMes.mesAtual(data.getMonth());
	}

	public static Date getPrimeiroDiaDoMesAtual() {

		return DataUtil.toDate(StringUtils.join("01/", DataUtil.getMesAtualEnum().getNumero(), "/", DataUtil.getAnoAtual()));
	}

	public static Date getPrimeiroDiaDoMes(Date data) {

		return DataUtil.toDate(StringUtils.join("01/", DominioMes.mesAtual(data.getMonth()).getNumero(), "/", DataUtil.getAno(data)));
	}

	public static Date getUltimoDiaDoMesAnterior() {

		return getUltimaDataDoMes(getDataMesAnterior());
	}

	public static Date getDataMesAnterior() {

		return DataUtil.removerMeses(DataUtil.getDataAtual(), 1);
	}

	public static Date removerMeses(final Date data, int meses) {

		return DateUtils.addMonths(data, -1 * meses);
	}

	public static Date getUltimoDiaDoMesAtual() {

		return getUltimaDataDoMes(DataUtil.getDataAtual());
	}

	public static Date getUltimaDataDoMes(final Date data) {

		return DateUtils.setDays(data, DateUtils.toCalendar(data).getActualMaximum(Calendar.DAY_OF_MONTH));
	}

	public static String dateToStringAmericano(Date date) {

		DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		String strDate = dateFormat.format(date);
		return strDate;
	}
	public static int getDiferencaEntreMeses(Date dataLimite, Date dataAtual) {

		return Months.monthsBetween(new DateTime(DataUtil.truncate(DateUtils.setDays(dataLimite, 1))),
				new DateTime(DataUtil.truncate(DateUtils.setDays(dataAtual, 1)))).getMonths();
	}

}
