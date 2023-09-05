package fr.irit.module1.antlr4;// Generated from SqlQuery.g4 by ANTLR 4.13.0

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.ATN;
import org.antlr.v4.runtime.atn.ATNDeserializer;
import org.antlr.v4.runtime.atn.ParserATNSimulator;
import org.antlr.v4.runtime.atn.PredictionContextCache;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.tree.ParseTreeListener;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.List;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue"})
public class SqlQueryParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.13.0", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		SELECT=1, FROM=2, WHERE=3, DOT=4, COMMA=5, ASTERISK=6, LEFT_PARENTHESIS=7, 
		RIGHT_PARENTHESIS=8, EQ=9, NOT=10, MINUS=11, PLUS=12, GT=13, GE=14, LT=15, 
		LE=16, NEQ=17, AND=18, OR=19, INTEGER_VALUE=20, DECIMAL_VALUE=21, IDENTIFIER=22, 
		WS=23;
	public static final int
		RULE_query = 0, RULE_selectClause = 1, RULE_selectItem = 2, RULE_fromClause = 3, 
		RULE_fromItem = 4, RULE_condition = 5, RULE_subCondition = 6, RULE_predicate = 7, 
		RULE_join = 8, RULE_comparisonOperator = 9, RULE_dotNotation = 10;
	private static String[] makeRuleNames() {
		return new String[] {
			"query", "selectClause", "selectItem", "fromClause", "fromItem", "condition", 
			"subCondition", "predicate", "join", "comparisonOperator", "dotNotation"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, null, null, null, "'.'", "','", "'*'", "'('", "')'", "'='", "'!'", 
			"'-'", "'+'", "'>'", "'>='", "'<'", "'<='", "'!='"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "SELECT", "FROM", "WHERE", "DOT", "COMMA", "ASTERISK", "LEFT_PARENTHESIS", 
			"RIGHT_PARENTHESIS", "EQ", "NOT", "MINUS", "PLUS", "GT", "GE", "LT", 
			"LE", "NEQ", "AND", "OR", "INTEGER_VALUE", "DECIMAL_VALUE", "IDENTIFIER", 
			"WS"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "SqlQuery.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public SqlQueryParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class QueryContext extends ParserRuleContext {
		public TerminalNode SELECT() { return getToken(SqlQueryParser.SELECT, 0); }
		public SelectClauseContext selectClause() {
			return getRuleContext(SelectClauseContext.class,0);
		}
		public TerminalNode FROM() { return getToken(SqlQueryParser.FROM, 0); }
		public FromClauseContext fromClause() {
			return getRuleContext(FromClauseContext.class,0);
		}
		public TerminalNode WHERE() { return getToken(SqlQueryParser.WHERE, 0); }
		public ConditionContext condition() {
			return getRuleContext(ConditionContext.class,0);
		}
		public QueryContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_query; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SqlQueryListener) ((SqlQueryListener)listener).enterQuery(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SqlQueryListener) ((SqlQueryListener)listener).exitQuery(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SqlQueryVisitor) return ((SqlQueryVisitor<? extends T>)visitor).visitQuery(this);
			else return visitor.visitChildren(this);
		}
	}

	public final QueryContext query() throws RecognitionException {
		QueryContext _localctx = new QueryContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_query);
		try {
			setState(34);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(22);
				match(SELECT);
				setState(23);
				selectClause();
				setState(24);
				match(FROM);
				setState(25);
				fromClause();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(27);
				match(SELECT);
				setState(28);
				selectClause();
				setState(29);
				match(FROM);
				setState(30);
				fromClause();
				setState(31);
				match(WHERE);
				setState(32);
				condition();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class SelectClauseContext extends ParserRuleContext {
		public List<SelectItemContext> selectItem() {
			return getRuleContexts(SelectItemContext.class);
		}
		public SelectItemContext selectItem(int i) {
			return getRuleContext(SelectItemContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(SqlQueryParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(SqlQueryParser.COMMA, i);
		}
		public TerminalNode ASTERISK() { return getToken(SqlQueryParser.ASTERISK, 0); }
		public SelectClauseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_selectClause; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SqlQueryListener) ((SqlQueryListener)listener).enterSelectClause(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SqlQueryListener) ((SqlQueryListener)listener).exitSelectClause(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SqlQueryVisitor) return ((SqlQueryVisitor<? extends T>)visitor).visitSelectClause(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SelectClauseContext selectClause() throws RecognitionException {
		SelectClauseContext _localctx = new SelectClauseContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_selectClause);
		int _la;
		try {
			setState(45);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case IDENTIFIER:
				enterOuterAlt(_localctx, 1);
				{
				setState(36);
				selectItem();
				setState(41);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(37);
					match(COMMA);
					setState(38);
					selectItem();
					}
					}
					setState(43);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				break;
			case ASTERISK:
				enterOuterAlt(_localctx, 2);
				{
				setState(44);
				match(ASTERISK);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class SelectItemContext extends ParserRuleContext {
		public DotNotationContext dotNotation() {
			return getRuleContext(DotNotationContext.class,0);
		}
		public SelectItemContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_selectItem; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SqlQueryListener) ((SqlQueryListener)listener).enterSelectItem(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SqlQueryListener) ((SqlQueryListener)listener).exitSelectItem(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SqlQueryVisitor) return ((SqlQueryVisitor<? extends T>)visitor).visitSelectItem(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SelectItemContext selectItem() throws RecognitionException {
		SelectItemContext _localctx = new SelectItemContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_selectItem);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(47);
			dotNotation();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class FromClauseContext extends ParserRuleContext {
		public List<FromItemContext> fromItem() {
			return getRuleContexts(FromItemContext.class);
		}
		public FromItemContext fromItem(int i) {
			return getRuleContext(FromItemContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(SqlQueryParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(SqlQueryParser.COMMA, i);
		}
		public FromClauseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fromClause; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SqlQueryListener) ((SqlQueryListener)listener).enterFromClause(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SqlQueryListener) ((SqlQueryListener)listener).exitFromClause(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SqlQueryVisitor) return ((SqlQueryVisitor<? extends T>)visitor).visitFromClause(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FromClauseContext fromClause() throws RecognitionException {
		FromClauseContext _localctx = new FromClauseContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_fromClause);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(49);
			fromItem();
			setState(54);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(50);
				match(COMMA);
				setState(51);
				fromItem();
				}
				}
				setState(56);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class FromItemContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(SqlQueryParser.IDENTIFIER, 0); }
		public FromItemContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fromItem; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SqlQueryListener) ((SqlQueryListener)listener).enterFromItem(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SqlQueryListener) ((SqlQueryListener)listener).exitFromItem(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SqlQueryVisitor) return ((SqlQueryVisitor<? extends T>)visitor).visitFromItem(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FromItemContext fromItem() throws RecognitionException {
		FromItemContext _localctx = new FromItemContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_fromItem);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(57);
			match(IDENTIFIER);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ConditionContext extends ParserRuleContext {
		public List<JoinContext> join() {
			return getRuleContexts(JoinContext.class);
		}
		public JoinContext join(int i) {
			return getRuleContext(JoinContext.class,i);
		}
		public List<PredicateContext> predicate() {
			return getRuleContexts(PredicateContext.class);
		}
		public PredicateContext predicate(int i) {
			return getRuleContext(PredicateContext.class,i);
		}
		public List<SubConditionContext> subCondition() {
			return getRuleContexts(SubConditionContext.class);
		}
		public SubConditionContext subCondition(int i) {
			return getRuleContext(SubConditionContext.class,i);
		}
		public List<TerminalNode> AND() { return getTokens(SqlQueryParser.AND); }
		public TerminalNode AND(int i) {
			return getToken(SqlQueryParser.AND, i);
		}
		public ConditionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_condition; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SqlQueryListener) ((SqlQueryListener)listener).enterCondition(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SqlQueryListener) ((SqlQueryListener)listener).exitCondition(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SqlQueryVisitor) return ((SqlQueryVisitor<? extends T>)visitor).visitCondition(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ConditionContext condition() throws RecognitionException {
		ConditionContext _localctx = new ConditionContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_condition);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(62);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
			case 1:
				{
				setState(59);
				join();
				}
				break;
			case 2:
				{
				setState(60);
				predicate();
				}
				break;
			case 3:
				{
				setState(61);
				subCondition();
				}
				break;
			}
			setState(72);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==AND) {
				{
				{
				setState(64);
				match(AND);
				setState(68);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
				case 1:
					{
					setState(65);
					join();
					}
					break;
				case 2:
					{
					setState(66);
					predicate();
					}
					break;
				case 3:
					{
					setState(67);
					subCondition();
					}
					break;
				}
				}
				}
				setState(74);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class SubConditionContext extends ParserRuleContext {
		public TerminalNode LEFT_PARENTHESIS() { return getToken(SqlQueryParser.LEFT_PARENTHESIS, 0); }
		public List<PredicateContext> predicate() {
			return getRuleContexts(PredicateContext.class);
		}
		public PredicateContext predicate(int i) {
			return getRuleContext(PredicateContext.class,i);
		}
		public TerminalNode OR() { return getToken(SqlQueryParser.OR, 0); }
		public TerminalNode RIGHT_PARENTHESIS() { return getToken(SqlQueryParser.RIGHT_PARENTHESIS, 0); }
		public SubConditionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_subCondition; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SqlQueryListener) ((SqlQueryListener)listener).enterSubCondition(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SqlQueryListener) ((SqlQueryListener)listener).exitSubCondition(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SqlQueryVisitor) return ((SqlQueryVisitor<? extends T>)visitor).visitSubCondition(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SubConditionContext subCondition() throws RecognitionException {
		SubConditionContext _localctx = new SubConditionContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_subCondition);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(75);
			match(LEFT_PARENTHESIS);
			setState(76);
			predicate();
			setState(77);
			match(OR);
			setState(78);
			predicate();
			setState(79);
			match(RIGHT_PARENTHESIS);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class PredicateContext extends ParserRuleContext {
		public Token value;
		public DotNotationContext dotNotation() {
			return getRuleContext(DotNotationContext.class,0);
		}
		public ComparisonOperatorContext comparisonOperator() {
			return getRuleContext(ComparisonOperatorContext.class,0);
		}
		public TerminalNode INTEGER_VALUE() { return getToken(SqlQueryParser.INTEGER_VALUE, 0); }
		public TerminalNode DECIMAL_VALUE() { return getToken(SqlQueryParser.DECIMAL_VALUE, 0); }
		public TerminalNode IDENTIFIER() { return getToken(SqlQueryParser.IDENTIFIER, 0); }
		public PredicateContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_predicate; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SqlQueryListener) ((SqlQueryListener)listener).enterPredicate(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SqlQueryListener) ((SqlQueryListener)listener).exitPredicate(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SqlQueryVisitor) return ((SqlQueryVisitor<? extends T>)visitor).visitPredicate(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PredicateContext predicate() throws RecognitionException {
		PredicateContext _localctx = new PredicateContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_predicate);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(81);
			dotNotation();
			setState(82);
			comparisonOperator();
			setState(83);
			((PredicateContext)_localctx).value = _input.LT(1);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 7340032L) != 0)) ) {
				((PredicateContext)_localctx).value = (Token)_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class JoinContext extends ParserRuleContext {
		public DotNotationContext leftCondition;
		public DotNotationContext rightCondition;
		public TerminalNode EQ() { return getToken(SqlQueryParser.EQ, 0); }
		public List<DotNotationContext> dotNotation() {
			return getRuleContexts(DotNotationContext.class);
		}
		public DotNotationContext dotNotation(int i) {
			return getRuleContext(DotNotationContext.class,i);
		}
		public JoinContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_join; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SqlQueryListener) ((SqlQueryListener)listener).enterJoin(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SqlQueryListener) ((SqlQueryListener)listener).exitJoin(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SqlQueryVisitor) return ((SqlQueryVisitor<? extends T>)visitor).visitJoin(this);
			else return visitor.visitChildren(this);
		}
	}

	public final JoinContext join() throws RecognitionException {
		JoinContext _localctx = new JoinContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_join);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(85);
			((JoinContext)_localctx).leftCondition = dotNotation();
			setState(86);
			match(EQ);
			setState(87);
			((JoinContext)_localctx).rightCondition = dotNotation();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ComparisonOperatorContext extends ParserRuleContext {
		public TerminalNode GT() { return getToken(SqlQueryParser.GT, 0); }
		public TerminalNode GE() { return getToken(SqlQueryParser.GE, 0); }
		public TerminalNode LT() { return getToken(SqlQueryParser.LT, 0); }
		public TerminalNode LE() { return getToken(SqlQueryParser.LE, 0); }
		public TerminalNode EQ() { return getToken(SqlQueryParser.EQ, 0); }
		public TerminalNode NEQ() { return getToken(SqlQueryParser.NEQ, 0); }
		public ComparisonOperatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_comparisonOperator; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SqlQueryListener) ((SqlQueryListener)listener).enterComparisonOperator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SqlQueryListener) ((SqlQueryListener)listener).exitComparisonOperator(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SqlQueryVisitor) return ((SqlQueryVisitor<? extends T>)visitor).visitComparisonOperator(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ComparisonOperatorContext comparisonOperator() throws RecognitionException {
		ComparisonOperatorContext _localctx = new ComparisonOperatorContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_comparisonOperator);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(89);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 254464L) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class DotNotationContext extends ParserRuleContext {
		public Token table;
		public Token column;
		public TerminalNode DOT() { return getToken(SqlQueryParser.DOT, 0); }
		public List<TerminalNode> IDENTIFIER() { return getTokens(SqlQueryParser.IDENTIFIER); }
		public TerminalNode IDENTIFIER(int i) {
			return getToken(SqlQueryParser.IDENTIFIER, i);
		}
		public DotNotationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_dotNotation; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SqlQueryListener) ((SqlQueryListener)listener).enterDotNotation(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SqlQueryListener) ((SqlQueryListener)listener).exitDotNotation(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SqlQueryVisitor) return ((SqlQueryVisitor<? extends T>)visitor).visitDotNotation(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DotNotationContext dotNotation() throws RecognitionException {
		DotNotationContext _localctx = new DotNotationContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_dotNotation);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(91);
			((DotNotationContext)_localctx).table = match(IDENTIFIER);
			setState(92);
			match(DOT);
			setState(93);
			((DotNotationContext)_localctx).column = match(IDENTIFIER);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\u0004\u0001\u0017`\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0002"+
		"\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0001\u0000\u0001\u0000\u0001"+
		"\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001"+
		"\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0003\u0000#\b\u0000\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0005\u0001(\b\u0001\n\u0001\f\u0001+\t"+
		"\u0001\u0001\u0001\u0003\u0001.\b\u0001\u0001\u0002\u0001\u0002\u0001"+
		"\u0003\u0001\u0003\u0001\u0003\u0005\u00035\b\u0003\n\u0003\f\u00038\t"+
		"\u0003\u0001\u0004\u0001\u0004\u0001\u0005\u0001\u0005\u0001\u0005\u0003"+
		"\u0005?\b\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0003"+
		"\u0005E\b\u0005\u0005\u0005G\b\u0005\n\u0005\f\u0005J\t\u0005\u0001\u0006"+
		"\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0007"+
		"\u0001\u0007\u0001\u0007\u0001\u0007\u0001\b\u0001\b\u0001\b\u0001\b\u0001"+
		"\t\u0001\t\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0000\u0000\u000b\u0000"+
		"\u0002\u0004\u0006\b\n\f\u000e\u0010\u0012\u0014\u0000\u0002\u0001\u0000"+
		"\u0014\u0016\u0002\u0000\t\t\r\u0011]\u0000\"\u0001\u0000\u0000\u0000"+
		"\u0002-\u0001\u0000\u0000\u0000\u0004/\u0001\u0000\u0000\u0000\u00061"+
		"\u0001\u0000\u0000\u0000\b9\u0001\u0000\u0000\u0000\n>\u0001\u0000\u0000"+
		"\u0000\fK\u0001\u0000\u0000\u0000\u000eQ\u0001\u0000\u0000\u0000\u0010"+
		"U\u0001\u0000\u0000\u0000\u0012Y\u0001\u0000\u0000\u0000\u0014[\u0001"+
		"\u0000\u0000\u0000\u0016\u0017\u0005\u0001\u0000\u0000\u0017\u0018\u0003"+
		"\u0002\u0001\u0000\u0018\u0019\u0005\u0002\u0000\u0000\u0019\u001a\u0003"+
		"\u0006\u0003\u0000\u001a#\u0001\u0000\u0000\u0000\u001b\u001c\u0005\u0001"+
		"\u0000\u0000\u001c\u001d\u0003\u0002\u0001\u0000\u001d\u001e\u0005\u0002"+
		"\u0000\u0000\u001e\u001f\u0003\u0006\u0003\u0000\u001f \u0005\u0003\u0000"+
		"\u0000 !\u0003\n\u0005\u0000!#\u0001\u0000\u0000\u0000\"\u0016\u0001\u0000"+
		"\u0000\u0000\"\u001b\u0001\u0000\u0000\u0000#\u0001\u0001\u0000\u0000"+
		"\u0000$)\u0003\u0004\u0002\u0000%&\u0005\u0005\u0000\u0000&(\u0003\u0004"+
		"\u0002\u0000\'%\u0001\u0000\u0000\u0000(+\u0001\u0000\u0000\u0000)\'\u0001"+
		"\u0000\u0000\u0000)*\u0001\u0000\u0000\u0000*.\u0001\u0000\u0000\u0000"+
		"+)\u0001\u0000\u0000\u0000,.\u0005\u0006\u0000\u0000-$\u0001\u0000\u0000"+
		"\u0000-,\u0001\u0000\u0000\u0000.\u0003\u0001\u0000\u0000\u0000/0\u0003"+
		"\u0014\n\u00000\u0005\u0001\u0000\u0000\u000016\u0003\b\u0004\u000023"+
		"\u0005\u0005\u0000\u000035\u0003\b\u0004\u000042\u0001\u0000\u0000\u0000"+
		"58\u0001\u0000\u0000\u000064\u0001\u0000\u0000\u000067\u0001\u0000\u0000"+
		"\u00007\u0007\u0001\u0000\u0000\u000086\u0001\u0000\u0000\u00009:\u0005"+
		"\u0016\u0000\u0000:\t\u0001\u0000\u0000\u0000;?\u0003\u0010\b\u0000<?"+
		"\u0003\u000e\u0007\u0000=?\u0003\f\u0006\u0000>;\u0001\u0000\u0000\u0000"+
		"><\u0001\u0000\u0000\u0000>=\u0001\u0000\u0000\u0000?H\u0001\u0000\u0000"+
		"\u0000@D\u0005\u0012\u0000\u0000AE\u0003\u0010\b\u0000BE\u0003\u000e\u0007"+
		"\u0000CE\u0003\f\u0006\u0000DA\u0001\u0000\u0000\u0000DB\u0001\u0000\u0000"+
		"\u0000DC\u0001\u0000\u0000\u0000EG\u0001\u0000\u0000\u0000F@\u0001\u0000"+
		"\u0000\u0000GJ\u0001\u0000\u0000\u0000HF\u0001\u0000\u0000\u0000HI\u0001"+
		"\u0000\u0000\u0000I\u000b\u0001\u0000\u0000\u0000JH\u0001\u0000\u0000"+
		"\u0000KL\u0005\u0007\u0000\u0000LM\u0003\u000e\u0007\u0000MN\u0005\u0013"+
		"\u0000\u0000NO\u0003\u000e\u0007\u0000OP\u0005\b\u0000\u0000P\r\u0001"+
		"\u0000\u0000\u0000QR\u0003\u0014\n\u0000RS\u0003\u0012\t\u0000ST\u0007"+
		"\u0000\u0000\u0000T\u000f\u0001\u0000\u0000\u0000UV\u0003\u0014\n\u0000"+
		"VW\u0005\t\u0000\u0000WX\u0003\u0014\n\u0000X\u0011\u0001\u0000\u0000"+
		"\u0000YZ\u0007\u0001\u0000\u0000Z\u0013\u0001\u0000\u0000\u0000[\\\u0005"+
		"\u0016\u0000\u0000\\]\u0005\u0004\u0000\u0000]^\u0005\u0016\u0000\u0000"+
		"^\u0015\u0001\u0000\u0000\u0000\u0007\")-6>DH";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}