package fr.irit.module1.antlr4;// Generated from MongoQuery.g4 by ANTLR 4.13.0

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
public class MongoQueryParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.13.0", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, AGGREGATE=5, MATCH=6, PROJECT=7, LOOKUP=8, 
		DOT=9, COLON=10, COMMA=11, ASTERISK=12, LEFT_BRACES=13, RIGHT_BRACES=14, 
		LEFT_BRACKET=15, RIGHT_BRACKET=16, LEFT_PARENTHESES=17, RIGHT_PARENTHESES=18, 
		DQUOTE=19, SQUOTE=20, EQ=21, NEQ=22, GT=23, GTE=24, LT=25, LTE=26, IN=27, 
		NIN=28, AND=29, OR=30, NOT=31, NOR=32, BOOLEAN_TRUE=33, BOOLEAN_FALSE=34, 
		INTEGER_VALUE=35, DECIMAL_VALUE=36, IDENTIFIER=37, WS=38;
	public static final int
		RULE_query = 0, RULE_matchStage = 1, RULE_composedPredicate = 2, RULE_predicate = 3, 
		RULE_value = 4, RULE_projectStage = 5, RULE_fieldsProjection = 6, RULE_lookupStage = 7, 
		RULE_from = 8, RULE_localField = 9, RULE_foreignField = 10, RULE_as = 11, 
		RULE_field = 12, RULE_comparisonOperator = 13, RULE_logicalOperator = 14;
	private static String[] makeRuleNames() {
		return new String[] {
			"query", "matchStage", "composedPredicate", "predicate", "value", "projectStage", 
			"fieldsProjection", "lookupStage", "from", "localField", "foreignField", 
			"as", "field", "comparisonOperator", "logicalOperator"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'from'", "'localField'", "'foreignField'", "'as'", "'aggregate'", 
			"'$match'", "'$project'", "'$lookup'", "'.'", "':'", "','", "'*'", "'{'", 
			"'}'", "'['", "']'", "'('", "')'", "'\"'", "'''", "'$eq'", "'$ne'", "'$gt'", 
			"'$gte'", "'$lt'", "'$lte'", "'$in'", "'$nin'", "'$and'", "'$or'", "'$not'", 
			"'$nor'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, "AGGREGATE", "MATCH", "PROJECT", "LOOKUP", 
			"DOT", "COLON", "COMMA", "ASTERISK", "LEFT_BRACES", "RIGHT_BRACES", "LEFT_BRACKET", 
			"RIGHT_BRACKET", "LEFT_PARENTHESES", "RIGHT_PARENTHESES", "DQUOTE", "SQUOTE", 
			"EQ", "NEQ", "GT", "GTE", "LT", "LTE", "IN", "NIN", "AND", "OR", "NOT", 
			"NOR", "BOOLEAN_TRUE", "BOOLEAN_FALSE", "INTEGER_VALUE", "DECIMAL_VALUE", 
			"IDENTIFIER", "WS"
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
	public String getGrammarFileName() { return "MongoQuery.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public MongoQueryParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class QueryContext extends ParserRuleContext {
		public Token collection;
		public TerminalNode DOT() { return getToken(MongoQueryParser.DOT, 0); }
		public TerminalNode AGGREGATE() { return getToken(MongoQueryParser.AGGREGATE, 0); }
		public TerminalNode LEFT_PARENTHESES() { return getToken(MongoQueryParser.LEFT_PARENTHESES, 0); }
		public TerminalNode LEFT_BRACKET() { return getToken(MongoQueryParser.LEFT_BRACKET, 0); }
		public TerminalNode RIGHT_BRACKET() { return getToken(MongoQueryParser.RIGHT_BRACKET, 0); }
		public TerminalNode RIGHT_PARENTHESES() { return getToken(MongoQueryParser.RIGHT_PARENTHESES, 0); }
		public TerminalNode IDENTIFIER() { return getToken(MongoQueryParser.IDENTIFIER, 0); }
		public MatchStageContext matchStage() {
			return getRuleContext(MatchStageContext.class,0);
		}
		public List<TerminalNode> COMMA() { return getTokens(MongoQueryParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(MongoQueryParser.COMMA, i);
		}
		public ProjectStageContext projectStage() {
			return getRuleContext(ProjectStageContext.class,0);
		}
		public List<LookupStageContext> lookupStage() {
			return getRuleContexts(LookupStageContext.class);
		}
		public LookupStageContext lookupStage(int i) {
			return getRuleContext(LookupStageContext.class,i);
		}
		public QueryContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_query; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MongoQueryListener) ((MongoQueryListener)listener).enterQuery(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MongoQueryListener) ((MongoQueryListener)listener).exitQuery(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MongoQueryVisitor) return ((MongoQueryVisitor<? extends T>)visitor).visitQuery(this);
			else return visitor.visitChildren(this);
		}
	}

	public final QueryContext query() throws RecognitionException {
		QueryContext _localctx = new QueryContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_query);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(30);
			((QueryContext)_localctx).collection = match(IDENTIFIER);
			setState(31);
			match(DOT);
			setState(32);
			match(AGGREGATE);
			setState(33);
			match(LEFT_PARENTHESES);
			setState(34);
			match(LEFT_BRACKET);
			setState(36);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
			case 1:
				{
				setState(35);
				matchStage();
				}
				break;
			}
			setState(39);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
			case 1:
				{
				setState(38);
				match(COMMA);
				}
				break;
			}
			setState(42);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,2,_ctx) ) {
			case 1:
				{
				setState(41);
				projectStage();
				}
				break;
			}
			setState(45);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==COMMA) {
				{
				setState(44);
				match(COMMA);
				}
			}

			setState(53);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,5,_ctx);
			while ( _alt!=1 && _alt!= ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1+1 ) {
					{
					{
					setState(47);
					lookupStage();
					setState(49);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==COMMA) {
						{
						setState(48);
						match(COMMA);
						}
					}

					}
					} 
				}
				setState(55);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,5,_ctx);
			}
			setState(56);
			match(RIGHT_BRACKET);
			setState(57);
			match(RIGHT_PARENTHESES);
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
	public static class MatchStageContext extends ParserRuleContext {
		public List<TerminalNode> LEFT_BRACES() { return getTokens(MongoQueryParser.LEFT_BRACES); }
		public TerminalNode LEFT_BRACES(int i) {
			return getToken(MongoQueryParser.LEFT_BRACES, i);
		}
		public TerminalNode MATCH() { return getToken(MongoQueryParser.MATCH, 0); }
		public TerminalNode COLON() { return getToken(MongoQueryParser.COLON, 0); }
		public List<TerminalNode> RIGHT_BRACES() { return getTokens(MongoQueryParser.RIGHT_BRACES); }
		public TerminalNode RIGHT_BRACES(int i) {
			return getToken(MongoQueryParser.RIGHT_BRACES, i);
		}
		public List<PredicateContext> predicate() {
			return getRuleContexts(PredicateContext.class);
		}
		public PredicateContext predicate(int i) {
			return getRuleContext(PredicateContext.class,i);
		}
		public List<ComposedPredicateContext> composedPredicate() {
			return getRuleContexts(ComposedPredicateContext.class);
		}
		public ComposedPredicateContext composedPredicate(int i) {
			return getRuleContext(ComposedPredicateContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(MongoQueryParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(MongoQueryParser.COMMA, i);
		}
		public MatchStageContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_matchStage; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MongoQueryListener) ((MongoQueryListener)listener).enterMatchStage(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MongoQueryListener) ((MongoQueryListener)listener).exitMatchStage(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MongoQueryVisitor) return ((MongoQueryVisitor<? extends T>)visitor).visitMatchStage(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MatchStageContext matchStage() throws RecognitionException {
		MatchStageContext _localctx = new MatchStageContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_matchStage);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(59);
			match(LEFT_BRACES);
			setState(60);
			match(MATCH);
			setState(61);
			match(COLON);
			setState(62);
			match(LEFT_BRACES);
			setState(72);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 138512696320L) != 0)) {
				{
				{
				setState(65);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case COLON:
				case IDENTIFIER:
					{
					setState(63);
					predicate();
					}
					break;
				case OR:
					{
					setState(64);
					composedPredicate();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(68);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==COMMA) {
					{
					setState(67);
					match(COMMA);
					}
				}

				}
				}
				setState(74);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(75);
			match(RIGHT_BRACES);
			setState(76);
			match(RIGHT_BRACES);
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
	public static class ComposedPredicateContext extends ParserRuleContext {
		public TerminalNode OR() { return getToken(MongoQueryParser.OR, 0); }
		public TerminalNode COLON() { return getToken(MongoQueryParser.COLON, 0); }
		public TerminalNode LEFT_BRACKET() { return getToken(MongoQueryParser.LEFT_BRACKET, 0); }
		public TerminalNode RIGHT_BRACKET() { return getToken(MongoQueryParser.RIGHT_BRACKET, 0); }
		public List<TerminalNode> LEFT_BRACES() { return getTokens(MongoQueryParser.LEFT_BRACES); }
		public TerminalNode LEFT_BRACES(int i) {
			return getToken(MongoQueryParser.LEFT_BRACES, i);
		}
		public List<PredicateContext> predicate() {
			return getRuleContexts(PredicateContext.class);
		}
		public PredicateContext predicate(int i) {
			return getRuleContext(PredicateContext.class,i);
		}
		public List<TerminalNode> RIGHT_BRACES() { return getTokens(MongoQueryParser.RIGHT_BRACES); }
		public TerminalNode RIGHT_BRACES(int i) {
			return getToken(MongoQueryParser.RIGHT_BRACES, i);
		}
		public List<TerminalNode> COMMA() { return getTokens(MongoQueryParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(MongoQueryParser.COMMA, i);
		}
		public ComposedPredicateContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_composedPredicate; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MongoQueryListener) ((MongoQueryListener)listener).enterComposedPredicate(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MongoQueryListener) ((MongoQueryListener)listener).exitComposedPredicate(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MongoQueryVisitor) return ((MongoQueryVisitor<? extends T>)visitor).visitComposedPredicate(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ComposedPredicateContext composedPredicate() throws RecognitionException {
		ComposedPredicateContext _localctx = new ComposedPredicateContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_composedPredicate);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(78);
			match(OR);
			setState(79);
			match(COLON);
			setState(80);
			match(LEFT_BRACKET);
			setState(89);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==LEFT_BRACES) {
				{
				{
				setState(81);
				match(LEFT_BRACES);
				setState(82);
				predicate();
				setState(83);
				match(RIGHT_BRACES);
				setState(85);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==COMMA) {
					{
					setState(84);
					match(COMMA);
					}
				}

				}
				}
				setState(91);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(92);
			match(RIGHT_BRACKET);
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
		public FieldContext field() {
			return getRuleContext(FieldContext.class,0);
		}
		public List<TerminalNode> COLON() { return getTokens(MongoQueryParser.COLON); }
		public TerminalNode COLON(int i) {
			return getToken(MongoQueryParser.COLON, i);
		}
		public ValueContext value() {
			return getRuleContext(ValueContext.class,0);
		}
		public TerminalNode LEFT_BRACES() { return getToken(MongoQueryParser.LEFT_BRACES, 0); }
		public ComparisonOperatorContext comparisonOperator() {
			return getRuleContext(ComparisonOperatorContext.class,0);
		}
		public TerminalNode RIGHT_BRACES() { return getToken(MongoQueryParser.RIGHT_BRACES, 0); }
		public PredicateContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_predicate; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MongoQueryListener) ((MongoQueryListener)listener).enterPredicate(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MongoQueryListener) ((MongoQueryListener)listener).exitPredicate(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MongoQueryVisitor) return ((MongoQueryVisitor<? extends T>)visitor).visitPredicate(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PredicateContext predicate() throws RecognitionException {
		PredicateContext _localctx = new PredicateContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_predicate);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(94);
			field();
			setState(95);
			match(COLON);
			setState(103);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case DQUOTE:
			case SQUOTE:
			case INTEGER_VALUE:
			case DECIMAL_VALUE:
			case IDENTIFIER:
				{
				setState(96);
				value();
				}
				break;
			case LEFT_BRACES:
				{
				setState(97);
				match(LEFT_BRACES);
				setState(98);
				comparisonOperator();
				setState(99);
				match(COLON);
				setState(100);
				value();
				setState(101);
				match(RIGHT_BRACES);
				}
				break;
			default:
				throw new NoViableAltException(this);
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
	public static class ValueContext extends ParserRuleContext {
		public Token raw;
		public TerminalNode IDENTIFIER() { return getToken(MongoQueryParser.IDENTIFIER, 0); }
		public List<TerminalNode> DQUOTE() { return getTokens(MongoQueryParser.DQUOTE); }
		public TerminalNode DQUOTE(int i) {
			return getToken(MongoQueryParser.DQUOTE, i);
		}
		public List<TerminalNode> SQUOTE() { return getTokens(MongoQueryParser.SQUOTE); }
		public TerminalNode SQUOTE(int i) {
			return getToken(MongoQueryParser.SQUOTE, i);
		}
		public TerminalNode INTEGER_VALUE() { return getToken(MongoQueryParser.INTEGER_VALUE, 0); }
		public TerminalNode DECIMAL_VALUE() { return getToken(MongoQueryParser.DECIMAL_VALUE, 0); }
		public ValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_value; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MongoQueryListener) ((MongoQueryListener)listener).enterValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MongoQueryListener) ((MongoQueryListener)listener).exitValue(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MongoQueryVisitor) return ((MongoQueryVisitor<? extends T>)visitor).visitValue(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ValueContext value() throws RecognitionException {
		ValueContext _localctx = new ValueContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_value);
		int _la;
		try {
			setState(124);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case DQUOTE:
			case SQUOTE:
			case IDENTIFIER:
				enterOuterAlt(_localctx, 1);
				{
				setState(111);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,14,_ctx) ) {
				case 1:
					{
					setState(106);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==DQUOTE) {
						{
						setState(105);
						match(DQUOTE);
						}
					}

					}
					break;
				case 2:
					{
					setState(109);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==SQUOTE) {
						{
						setState(108);
						match(SQUOTE);
						}
					}

					}
					break;
				}
				setState(113);
				((ValueContext)_localctx).raw = match(IDENTIFIER);
				setState(120);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,17,_ctx) ) {
				case 1:
					{
					setState(115);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==DQUOTE) {
						{
						setState(114);
						match(DQUOTE);
						}
					}

					}
					break;
				case 2:
					{
					setState(118);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==SQUOTE) {
						{
						setState(117);
						match(SQUOTE);
						}
					}

					}
					break;
				}
				}
				break;
			case INTEGER_VALUE:
				enterOuterAlt(_localctx, 2);
				{
				setState(122);
				((ValueContext)_localctx).raw = match(INTEGER_VALUE);
				}
				break;
			case DECIMAL_VALUE:
				enterOuterAlt(_localctx, 3);
				{
				setState(123);
				((ValueContext)_localctx).raw = match(DECIMAL_VALUE);
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
	public static class ProjectStageContext extends ParserRuleContext {
		public List<TerminalNode> LEFT_BRACES() { return getTokens(MongoQueryParser.LEFT_BRACES); }
		public TerminalNode LEFT_BRACES(int i) {
			return getToken(MongoQueryParser.LEFT_BRACES, i);
		}
		public TerminalNode PROJECT() { return getToken(MongoQueryParser.PROJECT, 0); }
		public TerminalNode COLON() { return getToken(MongoQueryParser.COLON, 0); }
		public FieldsProjectionContext fieldsProjection() {
			return getRuleContext(FieldsProjectionContext.class,0);
		}
		public List<TerminalNode> RIGHT_BRACES() { return getTokens(MongoQueryParser.RIGHT_BRACES); }
		public TerminalNode RIGHT_BRACES(int i) {
			return getToken(MongoQueryParser.RIGHT_BRACES, i);
		}
		public ProjectStageContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_projectStage; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MongoQueryListener) ((MongoQueryListener)listener).enterProjectStage(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MongoQueryListener) ((MongoQueryListener)listener).exitProjectStage(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MongoQueryVisitor) return ((MongoQueryVisitor<? extends T>)visitor).visitProjectStage(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProjectStageContext projectStage() throws RecognitionException {
		ProjectStageContext _localctx = new ProjectStageContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_projectStage);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(126);
			match(LEFT_BRACES);
			setState(127);
			match(PROJECT);
			setState(128);
			match(COLON);
			setState(129);
			match(LEFT_BRACES);
			setState(130);
			fieldsProjection();
			setState(131);
			match(RIGHT_BRACES);
			setState(132);
			match(RIGHT_BRACES);
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
	public static class FieldsProjectionContext extends ParserRuleContext {
		public List<FieldContext> field() {
			return getRuleContexts(FieldContext.class);
		}
		public FieldContext field(int i) {
			return getRuleContext(FieldContext.class,i);
		}
		public List<TerminalNode> COLON() { return getTokens(MongoQueryParser.COLON); }
		public TerminalNode COLON(int i) {
			return getToken(MongoQueryParser.COLON, i);
		}
		public List<TerminalNode> BOOLEAN_TRUE() { return getTokens(MongoQueryParser.BOOLEAN_TRUE); }
		public TerminalNode BOOLEAN_TRUE(int i) {
			return getToken(MongoQueryParser.BOOLEAN_TRUE, i);
		}
		public List<TerminalNode> BOOLEAN_FALSE() { return getTokens(MongoQueryParser.BOOLEAN_FALSE); }
		public TerminalNode BOOLEAN_FALSE(int i) {
			return getToken(MongoQueryParser.BOOLEAN_FALSE, i);
		}
		public List<TerminalNode> COMMA() { return getTokens(MongoQueryParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(MongoQueryParser.COMMA, i);
		}
		public FieldsProjectionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fieldsProjection; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MongoQueryListener) ((MongoQueryListener)listener).enterFieldsProjection(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MongoQueryListener) ((MongoQueryListener)listener).exitFieldsProjection(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MongoQueryVisitor) return ((MongoQueryVisitor<? extends T>)visitor).visitFieldsProjection(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FieldsProjectionContext fieldsProjection() throws RecognitionException {
		FieldsProjectionContext _localctx = new FieldsProjectionContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_fieldsProjection);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(142);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COLON || _la==IDENTIFIER) {
				{
				{
				setState(134);
				field();
				setState(135);
				match(COLON);
				setState(136);
				_la = _input.LA(1);
				if ( !(_la==BOOLEAN_TRUE || _la==BOOLEAN_FALSE) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(138);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==COMMA) {
					{
					setState(137);
					match(COMMA);
					}
				}

				}
				}
				setState(144);
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
	public static class LookupStageContext extends ParserRuleContext {
		public List<TerminalNode> LEFT_BRACES() { return getTokens(MongoQueryParser.LEFT_BRACES); }
		public TerminalNode LEFT_BRACES(int i) {
			return getToken(MongoQueryParser.LEFT_BRACES, i);
		}
		public TerminalNode LOOKUP() { return getToken(MongoQueryParser.LOOKUP, 0); }
		public TerminalNode COLON() { return getToken(MongoQueryParser.COLON, 0); }
		public FromContext from() {
			return getRuleContext(FromContext.class,0);
		}
		public List<TerminalNode> COMMA() { return getTokens(MongoQueryParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(MongoQueryParser.COMMA, i);
		}
		public LocalFieldContext localField() {
			return getRuleContext(LocalFieldContext.class,0);
		}
		public ForeignFieldContext foreignField() {
			return getRuleContext(ForeignFieldContext.class,0);
		}
		public AsContext as() {
			return getRuleContext(AsContext.class,0);
		}
		public List<TerminalNode> RIGHT_BRACES() { return getTokens(MongoQueryParser.RIGHT_BRACES); }
		public TerminalNode RIGHT_BRACES(int i) {
			return getToken(MongoQueryParser.RIGHT_BRACES, i);
		}
		public LookupStageContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_lookupStage; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MongoQueryListener) ((MongoQueryListener)listener).enterLookupStage(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MongoQueryListener) ((MongoQueryListener)listener).exitLookupStage(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MongoQueryVisitor) return ((MongoQueryVisitor<? extends T>)visitor).visitLookupStage(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LookupStageContext lookupStage() throws RecognitionException {
		LookupStageContext _localctx = new LookupStageContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_lookupStage);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(145);
			match(LEFT_BRACES);
			setState(146);
			match(LOOKUP);
			setState(147);
			match(COLON);
			setState(148);
			match(LEFT_BRACES);
			setState(149);
			from();
			setState(150);
			match(COMMA);
			setState(151);
			localField();
			setState(152);
			match(COMMA);
			setState(153);
			foreignField();
			setState(154);
			match(COMMA);
			setState(155);
			as();
			setState(156);
			match(RIGHT_BRACES);
			setState(157);
			match(RIGHT_BRACES);
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
	public static class FromContext extends ParserRuleContext {
		public Token collection;
		public TerminalNode COLON() { return getToken(MongoQueryParser.COLON, 0); }
		public TerminalNode IDENTIFIER() { return getToken(MongoQueryParser.IDENTIFIER, 0); }
		public List<TerminalNode> DQUOTE() { return getTokens(MongoQueryParser.DQUOTE); }
		public TerminalNode DQUOTE(int i) {
			return getToken(MongoQueryParser.DQUOTE, i);
		}
		public List<TerminalNode> SQUOTE() { return getTokens(MongoQueryParser.SQUOTE); }
		public TerminalNode SQUOTE(int i) {
			return getToken(MongoQueryParser.SQUOTE, i);
		}
		public FromContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_from; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MongoQueryListener) ((MongoQueryListener)listener).enterFrom(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MongoQueryListener) ((MongoQueryListener)listener).exitFrom(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MongoQueryVisitor) return ((MongoQueryVisitor<? extends T>)visitor).visitFrom(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FromContext from() throws RecognitionException {
		FromContext _localctx = new FromContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_from);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(159);
			match(T__0);
			setState(160);
			match(COLON);
			setState(167);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,23,_ctx) ) {
			case 1:
				{
				setState(162);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==DQUOTE) {
					{
					setState(161);
					match(DQUOTE);
					}
				}

				}
				break;
			case 2:
				{
				setState(165);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==SQUOTE) {
					{
					setState(164);
					match(SQUOTE);
					}
				}

				}
				break;
			}
			setState(169);
			((FromContext)_localctx).collection = match(IDENTIFIER);
			setState(176);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,26,_ctx) ) {
			case 1:
				{
				setState(171);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==DQUOTE) {
					{
					setState(170);
					match(DQUOTE);
					}
				}

				}
				break;
			case 2:
				{
				setState(174);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==SQUOTE) {
					{
					setState(173);
					match(SQUOTE);
					}
				}

				}
				break;
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
	public static class LocalFieldContext extends ParserRuleContext {
		public TerminalNode COLON() { return getToken(MongoQueryParser.COLON, 0); }
		public FieldContext field() {
			return getRuleContext(FieldContext.class,0);
		}
		public List<TerminalNode> DQUOTE() { return getTokens(MongoQueryParser.DQUOTE); }
		public TerminalNode DQUOTE(int i) {
			return getToken(MongoQueryParser.DQUOTE, i);
		}
		public List<TerminalNode> SQUOTE() { return getTokens(MongoQueryParser.SQUOTE); }
		public TerminalNode SQUOTE(int i) {
			return getToken(MongoQueryParser.SQUOTE, i);
		}
		public LocalFieldContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_localField; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MongoQueryListener) ((MongoQueryListener)listener).enterLocalField(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MongoQueryListener) ((MongoQueryListener)listener).exitLocalField(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MongoQueryVisitor) return ((MongoQueryVisitor<? extends T>)visitor).visitLocalField(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LocalFieldContext localField() throws RecognitionException {
		LocalFieldContext _localctx = new LocalFieldContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_localField);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(178);
			match(T__1);
			setState(179);
			match(COLON);
			setState(186);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,29,_ctx) ) {
			case 1:
				{
				setState(181);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,27,_ctx) ) {
				case 1:
					{
					setState(180);
					match(DQUOTE);
					}
					break;
				}
				}
				break;
			case 2:
				{
				setState(184);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,28,_ctx) ) {
				case 1:
					{
					setState(183);
					match(SQUOTE);
					}
					break;
				}
				}
				break;
			}
			setState(188);
			field();
			setState(195);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,32,_ctx) ) {
			case 1:
				{
				setState(190);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==DQUOTE) {
					{
					setState(189);
					match(DQUOTE);
					}
				}

				}
				break;
			case 2:
				{
				setState(193);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==SQUOTE) {
					{
					setState(192);
					match(SQUOTE);
					}
				}

				}
				break;
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
	public static class ForeignFieldContext extends ParserRuleContext {
		public TerminalNode COLON() { return getToken(MongoQueryParser.COLON, 0); }
		public FieldContext field() {
			return getRuleContext(FieldContext.class,0);
		}
		public List<TerminalNode> DQUOTE() { return getTokens(MongoQueryParser.DQUOTE); }
		public TerminalNode DQUOTE(int i) {
			return getToken(MongoQueryParser.DQUOTE, i);
		}
		public List<TerminalNode> SQUOTE() { return getTokens(MongoQueryParser.SQUOTE); }
		public TerminalNode SQUOTE(int i) {
			return getToken(MongoQueryParser.SQUOTE, i);
		}
		public ForeignFieldContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_foreignField; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MongoQueryListener) ((MongoQueryListener)listener).enterForeignField(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MongoQueryListener) ((MongoQueryListener)listener).exitForeignField(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MongoQueryVisitor) return ((MongoQueryVisitor<? extends T>)visitor).visitForeignField(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ForeignFieldContext foreignField() throws RecognitionException {
		ForeignFieldContext _localctx = new ForeignFieldContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_foreignField);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(197);
			match(T__2);
			setState(198);
			match(COLON);
			setState(205);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,35,_ctx) ) {
			case 1:
				{
				setState(200);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,33,_ctx) ) {
				case 1:
					{
					setState(199);
					match(DQUOTE);
					}
					break;
				}
				}
				break;
			case 2:
				{
				setState(203);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,34,_ctx) ) {
				case 1:
					{
					setState(202);
					match(SQUOTE);
					}
					break;
				}
				}
				break;
			}
			setState(207);
			field();
			setState(214);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,38,_ctx) ) {
			case 1:
				{
				setState(209);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==DQUOTE) {
					{
					setState(208);
					match(DQUOTE);
					}
				}

				}
				break;
			case 2:
				{
				setState(212);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==SQUOTE) {
					{
					setState(211);
					match(SQUOTE);
					}
				}

				}
				break;
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
	public static class AsContext extends ParserRuleContext {
		public Token rename;
		public TerminalNode COLON() { return getToken(MongoQueryParser.COLON, 0); }
		public TerminalNode IDENTIFIER() { return getToken(MongoQueryParser.IDENTIFIER, 0); }
		public List<TerminalNode> DQUOTE() { return getTokens(MongoQueryParser.DQUOTE); }
		public TerminalNode DQUOTE(int i) {
			return getToken(MongoQueryParser.DQUOTE, i);
		}
		public List<TerminalNode> SQUOTE() { return getTokens(MongoQueryParser.SQUOTE); }
		public TerminalNode SQUOTE(int i) {
			return getToken(MongoQueryParser.SQUOTE, i);
		}
		public AsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_as; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MongoQueryListener) ((MongoQueryListener)listener).enterAs(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MongoQueryListener) ((MongoQueryListener)listener).exitAs(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MongoQueryVisitor) return ((MongoQueryVisitor<? extends T>)visitor).visitAs(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AsContext as() throws RecognitionException {
		AsContext _localctx = new AsContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_as);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(216);
			match(T__3);
			setState(217);
			match(COLON);
			setState(224);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,41,_ctx) ) {
			case 1:
				{
				setState(219);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==DQUOTE) {
					{
					setState(218);
					match(DQUOTE);
					}
				}

				}
				break;
			case 2:
				{
				setState(222);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==SQUOTE) {
					{
					setState(221);
					match(SQUOTE);
					}
				}

				}
				break;
			}
			setState(226);
			((AsContext)_localctx).rename = match(IDENTIFIER);
			setState(233);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,44,_ctx) ) {
			case 1:
				{
				setState(228);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==DQUOTE) {
					{
					setState(227);
					match(DQUOTE);
					}
				}

				}
				break;
			case 2:
				{
				setState(231);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==SQUOTE) {
					{
					setState(230);
					match(SQUOTE);
					}
				}

				}
				break;
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
	public static class FieldContext extends ParserRuleContext {
		public List<TerminalNode> IDENTIFIER() { return getTokens(MongoQueryParser.IDENTIFIER); }
		public TerminalNode IDENTIFIER(int i) {
			return getToken(MongoQueryParser.IDENTIFIER, i);
		}
		public List<TerminalNode> DOT() { return getTokens(MongoQueryParser.DOT); }
		public TerminalNode DOT(int i) {
			return getToken(MongoQueryParser.DOT, i);
		}
		public FieldContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_field; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MongoQueryListener) ((MongoQueryListener)listener).enterField(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MongoQueryListener) ((MongoQueryListener)listener).exitField(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MongoQueryVisitor) return ((MongoQueryVisitor<? extends T>)visitor).visitField(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FieldContext field() throws RecognitionException {
		FieldContext _localctx = new FieldContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_field);
		int _la;
		try {
			setState(245);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,47,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(235);
				match(IDENTIFIER);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(242);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==IDENTIFIER) {
					{
					{
					setState(236);
					match(IDENTIFIER);
					setState(238);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==DOT) {
						{
						setState(237);
						match(DOT);
						}
					}

					}
					}
					setState(244);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
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
	public static class ComparisonOperatorContext extends ParserRuleContext {
		public TerminalNode EQ() { return getToken(MongoQueryParser.EQ, 0); }
		public TerminalNode NEQ() { return getToken(MongoQueryParser.NEQ, 0); }
		public TerminalNode GT() { return getToken(MongoQueryParser.GT, 0); }
		public TerminalNode GTE() { return getToken(MongoQueryParser.GTE, 0); }
		public TerminalNode LT() { return getToken(MongoQueryParser.LT, 0); }
		public TerminalNode LTE() { return getToken(MongoQueryParser.LTE, 0); }
		public TerminalNode IN() { return getToken(MongoQueryParser.IN, 0); }
		public TerminalNode NIN() { return getToken(MongoQueryParser.NIN, 0); }
		public ComparisonOperatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_comparisonOperator; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MongoQueryListener) ((MongoQueryListener)listener).enterComparisonOperator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MongoQueryListener) ((MongoQueryListener)listener).exitComparisonOperator(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MongoQueryVisitor) return ((MongoQueryVisitor<? extends T>)visitor).visitComparisonOperator(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ComparisonOperatorContext comparisonOperator() throws RecognitionException {
		ComparisonOperatorContext _localctx = new ComparisonOperatorContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_comparisonOperator);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(247);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 534773760L) != 0)) ) {
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
	public static class LogicalOperatorContext extends ParserRuleContext {
		public TerminalNode AND() { return getToken(MongoQueryParser.AND, 0); }
		public TerminalNode OR() { return getToken(MongoQueryParser.OR, 0); }
		public TerminalNode NOR() { return getToken(MongoQueryParser.NOR, 0); }
		public TerminalNode NOT() { return getToken(MongoQueryParser.NOT, 0); }
		public LogicalOperatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_logicalOperator; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MongoQueryListener) ((MongoQueryListener)listener).enterLogicalOperator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MongoQueryListener) ((MongoQueryListener)listener).exitLogicalOperator(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MongoQueryVisitor) return ((MongoQueryVisitor<? extends T>)visitor).visitLogicalOperator(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LogicalOperatorContext logicalOperator() throws RecognitionException {
		LogicalOperatorContext _localctx = new LogicalOperatorContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_logicalOperator);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(249);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 8053063680L) != 0)) ) {
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

	public static final String _serializedATN =
		"\u0004\u0001&\u00fc\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0002"+
		"\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b\u0002"+
		"\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0001\u0000\u0001\u0000"+
		"\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0003\u0000%\b\u0000"+
		"\u0001\u0000\u0003\u0000(\b\u0000\u0001\u0000\u0003\u0000+\b\u0000\u0001"+
		"\u0000\u0003\u0000.\b\u0000\u0001\u0000\u0001\u0000\u0003\u00002\b\u0000"+
		"\u0005\u00004\b\u0000\n\u0000\f\u00007\t\u0000\u0001\u0000\u0001\u0000"+
		"\u0001\u0000\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0003\u0001B\b\u0001\u0001\u0001\u0003\u0001E\b\u0001\u0005"+
		"\u0001G\b\u0001\n\u0001\f\u0001J\t\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001"+
		"\u0002\u0001\u0002\u0003\u0002V\b\u0002\u0005\u0002X\b\u0002\n\u0002\f"+
		"\u0002[\t\u0002\u0001\u0002\u0001\u0002\u0001\u0003\u0001\u0003\u0001"+
		"\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001"+
		"\u0003\u0003\u0003h\b\u0003\u0001\u0004\u0003\u0004k\b\u0004\u0001\u0004"+
		"\u0003\u0004n\b\u0004\u0003\u0004p\b\u0004\u0001\u0004\u0001\u0004\u0003"+
		"\u0004t\b\u0004\u0001\u0004\u0003\u0004w\b\u0004\u0003\u0004y\b\u0004"+
		"\u0001\u0004\u0001\u0004\u0003\u0004}\b\u0004\u0001\u0005\u0001\u0005"+
		"\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005"+
		"\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0003\u0006\u008b\b\u0006"+
		"\u0005\u0006\u008d\b\u0006\n\u0006\f\u0006\u0090\t\u0006\u0001\u0007\u0001"+
		"\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001"+
		"\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001"+
		"\u0007\u0001\b\u0001\b\u0001\b\u0003\b\u00a3\b\b\u0001\b\u0003\b\u00a6"+
		"\b\b\u0003\b\u00a8\b\b\u0001\b\u0001\b\u0003\b\u00ac\b\b\u0001\b\u0003"+
		"\b\u00af\b\b\u0003\b\u00b1\b\b\u0001\t\u0001\t\u0001\t\u0003\t\u00b6\b"+
		"\t\u0001\t\u0003\t\u00b9\b\t\u0003\t\u00bb\b\t\u0001\t\u0001\t\u0003\t"+
		"\u00bf\b\t\u0001\t\u0003\t\u00c2\b\t\u0003\t\u00c4\b\t\u0001\n\u0001\n"+
		"\u0001\n\u0003\n\u00c9\b\n\u0001\n\u0003\n\u00cc\b\n\u0003\n\u00ce\b\n"+
		"\u0001\n\u0001\n\u0003\n\u00d2\b\n\u0001\n\u0003\n\u00d5\b\n\u0003\n\u00d7"+
		"\b\n\u0001\u000b\u0001\u000b\u0001\u000b\u0003\u000b\u00dc\b\u000b\u0001"+
		"\u000b\u0003\u000b\u00df\b\u000b\u0003\u000b\u00e1\b\u000b\u0001\u000b"+
		"\u0001\u000b\u0003\u000b\u00e5\b\u000b\u0001\u000b\u0003\u000b\u00e8\b"+
		"\u000b\u0003\u000b\u00ea\b\u000b\u0001\f\u0001\f\u0001\f\u0003\f\u00ef"+
		"\b\f\u0005\f\u00f1\b\f\n\f\f\f\u00f4\t\f\u0003\f\u00f6\b\f\u0001\r\u0001"+
		"\r\u0001\u000e\u0001\u000e\u0001\u000e\u00015\u0000\u000f\u0000\u0002"+
		"\u0004\u0006\b\n\f\u000e\u0010\u0012\u0014\u0016\u0018\u001a\u001c\u0000"+
		"\u0003\u0001\u0000!\"\u0001\u0000\u0015\u001c\u0001\u0000\u001d \u011d"+
		"\u0000\u001e\u0001\u0000\u0000\u0000\u0002;\u0001\u0000\u0000\u0000\u0004"+
		"N\u0001\u0000\u0000\u0000\u0006^\u0001\u0000\u0000\u0000\b|\u0001\u0000"+
		"\u0000\u0000\n~\u0001\u0000\u0000\u0000\f\u008e\u0001\u0000\u0000\u0000"+
		"\u000e\u0091\u0001\u0000\u0000\u0000\u0010\u009f\u0001\u0000\u0000\u0000"+
		"\u0012\u00b2\u0001\u0000\u0000\u0000\u0014\u00c5\u0001\u0000\u0000\u0000"+
		"\u0016\u00d8\u0001\u0000\u0000\u0000\u0018\u00f5\u0001\u0000\u0000\u0000"+
		"\u001a\u00f7\u0001\u0000\u0000\u0000\u001c\u00f9\u0001\u0000\u0000\u0000"+
		"\u001e\u001f\u0005%\u0000\u0000\u001f \u0005\t\u0000\u0000 !\u0005\u0005"+
		"\u0000\u0000!\"\u0005\u0011\u0000\u0000\"$\u0005\u000f\u0000\u0000#%\u0003"+
		"\u0002\u0001\u0000$#\u0001\u0000\u0000\u0000$%\u0001\u0000\u0000\u0000"+
		"%\'\u0001\u0000\u0000\u0000&(\u0005\u000b\u0000\u0000\'&\u0001\u0000\u0000"+
		"\u0000\'(\u0001\u0000\u0000\u0000(*\u0001\u0000\u0000\u0000)+\u0003\n"+
		"\u0005\u0000*)\u0001\u0000\u0000\u0000*+\u0001\u0000\u0000\u0000+-\u0001"+
		"\u0000\u0000\u0000,.\u0005\u000b\u0000\u0000-,\u0001\u0000\u0000\u0000"+
		"-.\u0001\u0000\u0000\u0000.5\u0001\u0000\u0000\u0000/1\u0003\u000e\u0007"+
		"\u000002\u0005\u000b\u0000\u000010\u0001\u0000\u0000\u000012\u0001\u0000"+
		"\u0000\u000024\u0001\u0000\u0000\u00003/\u0001\u0000\u0000\u000047\u0001"+
		"\u0000\u0000\u000056\u0001\u0000\u0000\u000053\u0001\u0000\u0000\u0000"+
		"68\u0001\u0000\u0000\u000075\u0001\u0000\u0000\u000089\u0005\u0010\u0000"+
		"\u00009:\u0005\u0012\u0000\u0000:\u0001\u0001\u0000\u0000\u0000;<\u0005"+
		"\r\u0000\u0000<=\u0005\u0006\u0000\u0000=>\u0005\n\u0000\u0000>H\u0005"+
		"\r\u0000\u0000?B\u0003\u0006\u0003\u0000@B\u0003\u0004\u0002\u0000A?\u0001"+
		"\u0000\u0000\u0000A@\u0001\u0000\u0000\u0000BD\u0001\u0000\u0000\u0000"+
		"CE\u0005\u000b\u0000\u0000DC\u0001\u0000\u0000\u0000DE\u0001\u0000\u0000"+
		"\u0000EG\u0001\u0000\u0000\u0000FA\u0001\u0000\u0000\u0000GJ\u0001\u0000"+
		"\u0000\u0000HF\u0001\u0000\u0000\u0000HI\u0001\u0000\u0000\u0000IK\u0001"+
		"\u0000\u0000\u0000JH\u0001\u0000\u0000\u0000KL\u0005\u000e\u0000\u0000"+
		"LM\u0005\u000e\u0000\u0000M\u0003\u0001\u0000\u0000\u0000NO\u0005\u001e"+
		"\u0000\u0000OP\u0005\n\u0000\u0000PY\u0005\u000f\u0000\u0000QR\u0005\r"+
		"\u0000\u0000RS\u0003\u0006\u0003\u0000SU\u0005\u000e\u0000\u0000TV\u0005"+
		"\u000b\u0000\u0000UT\u0001\u0000\u0000\u0000UV\u0001\u0000\u0000\u0000"+
		"VX\u0001\u0000\u0000\u0000WQ\u0001\u0000\u0000\u0000X[\u0001\u0000\u0000"+
		"\u0000YW\u0001\u0000\u0000\u0000YZ\u0001\u0000\u0000\u0000Z\\\u0001\u0000"+
		"\u0000\u0000[Y\u0001\u0000\u0000\u0000\\]\u0005\u0010\u0000\u0000]\u0005"+
		"\u0001\u0000\u0000\u0000^_\u0003\u0018\f\u0000_g\u0005\n\u0000\u0000`"+
		"h\u0003\b\u0004\u0000ab\u0005\r\u0000\u0000bc\u0003\u001a\r\u0000cd\u0005"+
		"\n\u0000\u0000de\u0003\b\u0004\u0000ef\u0005\u000e\u0000\u0000fh\u0001"+
		"\u0000\u0000\u0000g`\u0001\u0000\u0000\u0000ga\u0001\u0000\u0000\u0000"+
		"h\u0007\u0001\u0000\u0000\u0000ik\u0005\u0013\u0000\u0000ji\u0001\u0000"+
		"\u0000\u0000jk\u0001\u0000\u0000\u0000kp\u0001\u0000\u0000\u0000ln\u0005"+
		"\u0014\u0000\u0000ml\u0001\u0000\u0000\u0000mn\u0001\u0000\u0000\u0000"+
		"np\u0001\u0000\u0000\u0000oj\u0001\u0000\u0000\u0000om\u0001\u0000\u0000"+
		"\u0000pq\u0001\u0000\u0000\u0000qx\u0005%\u0000\u0000rt\u0005\u0013\u0000"+
		"\u0000sr\u0001\u0000\u0000\u0000st\u0001\u0000\u0000\u0000ty\u0001\u0000"+
		"\u0000\u0000uw\u0005\u0014\u0000\u0000vu\u0001\u0000\u0000\u0000vw\u0001"+
		"\u0000\u0000\u0000wy\u0001\u0000\u0000\u0000xs\u0001\u0000\u0000\u0000"+
		"xv\u0001\u0000\u0000\u0000y}\u0001\u0000\u0000\u0000z}\u0005#\u0000\u0000"+
		"{}\u0005$\u0000\u0000|o\u0001\u0000\u0000\u0000|z\u0001\u0000\u0000\u0000"+
		"|{\u0001\u0000\u0000\u0000}\t\u0001\u0000\u0000\u0000~\u007f\u0005\r\u0000"+
		"\u0000\u007f\u0080\u0005\u0007\u0000\u0000\u0080\u0081\u0005\n\u0000\u0000"+
		"\u0081\u0082\u0005\r\u0000\u0000\u0082\u0083\u0003\f\u0006\u0000\u0083"+
		"\u0084\u0005\u000e\u0000\u0000\u0084\u0085\u0005\u000e\u0000\u0000\u0085"+
		"\u000b\u0001\u0000\u0000\u0000\u0086\u0087\u0003\u0018\f\u0000\u0087\u0088"+
		"\u0005\n\u0000\u0000\u0088\u008a\u0007\u0000\u0000\u0000\u0089\u008b\u0005"+
		"\u000b\u0000\u0000\u008a\u0089\u0001\u0000\u0000\u0000\u008a\u008b\u0001"+
		"\u0000\u0000\u0000\u008b\u008d\u0001\u0000\u0000\u0000\u008c\u0086\u0001"+
		"\u0000\u0000\u0000\u008d\u0090\u0001\u0000\u0000\u0000\u008e\u008c\u0001"+
		"\u0000\u0000\u0000\u008e\u008f\u0001\u0000\u0000\u0000\u008f\r\u0001\u0000"+
		"\u0000\u0000\u0090\u008e\u0001\u0000\u0000\u0000\u0091\u0092\u0005\r\u0000"+
		"\u0000\u0092\u0093\u0005\b\u0000\u0000\u0093\u0094\u0005\n\u0000\u0000"+
		"\u0094\u0095\u0005\r\u0000\u0000\u0095\u0096\u0003\u0010\b\u0000\u0096"+
		"\u0097\u0005\u000b\u0000\u0000\u0097\u0098\u0003\u0012\t\u0000\u0098\u0099"+
		"\u0005\u000b\u0000\u0000\u0099\u009a\u0003\u0014\n\u0000\u009a\u009b\u0005"+
		"\u000b\u0000\u0000\u009b\u009c\u0003\u0016\u000b\u0000\u009c\u009d\u0005"+
		"\u000e\u0000\u0000\u009d\u009e\u0005\u000e\u0000\u0000\u009e\u000f\u0001"+
		"\u0000\u0000\u0000\u009f\u00a0\u0005\u0001\u0000\u0000\u00a0\u00a7\u0005"+
		"\n\u0000\u0000\u00a1\u00a3\u0005\u0013\u0000\u0000\u00a2\u00a1\u0001\u0000"+
		"\u0000\u0000\u00a2\u00a3\u0001\u0000\u0000\u0000\u00a3\u00a8\u0001\u0000"+
		"\u0000\u0000\u00a4\u00a6\u0005\u0014\u0000\u0000\u00a5\u00a4\u0001\u0000"+
		"\u0000\u0000\u00a5\u00a6\u0001\u0000\u0000\u0000\u00a6\u00a8\u0001\u0000"+
		"\u0000\u0000\u00a7\u00a2\u0001\u0000\u0000\u0000\u00a7\u00a5\u0001\u0000"+
		"\u0000\u0000\u00a8\u00a9\u0001\u0000\u0000\u0000\u00a9\u00b0\u0005%\u0000"+
		"\u0000\u00aa\u00ac\u0005\u0013\u0000\u0000\u00ab\u00aa\u0001\u0000\u0000"+
		"\u0000\u00ab\u00ac\u0001\u0000\u0000\u0000\u00ac\u00b1\u0001\u0000\u0000"+
		"\u0000\u00ad\u00af\u0005\u0014\u0000\u0000\u00ae\u00ad\u0001\u0000\u0000"+
		"\u0000\u00ae\u00af\u0001\u0000\u0000\u0000\u00af\u00b1\u0001\u0000\u0000"+
		"\u0000\u00b0\u00ab\u0001\u0000\u0000\u0000\u00b0\u00ae\u0001\u0000\u0000"+
		"\u0000\u00b1\u0011\u0001\u0000\u0000\u0000\u00b2\u00b3\u0005\u0002\u0000"+
		"\u0000\u00b3\u00ba\u0005\n\u0000\u0000\u00b4\u00b6\u0005\u0013\u0000\u0000"+
		"\u00b5\u00b4\u0001\u0000\u0000\u0000\u00b5\u00b6\u0001\u0000\u0000\u0000"+
		"\u00b6\u00bb\u0001\u0000\u0000\u0000\u00b7\u00b9\u0005\u0014\u0000\u0000"+
		"\u00b8\u00b7\u0001\u0000\u0000\u0000\u00b8\u00b9\u0001\u0000\u0000\u0000"+
		"\u00b9\u00bb\u0001\u0000\u0000\u0000\u00ba\u00b5\u0001\u0000\u0000\u0000"+
		"\u00ba\u00b8\u0001\u0000\u0000\u0000\u00bb\u00bc\u0001\u0000\u0000\u0000"+
		"\u00bc\u00c3\u0003\u0018\f\u0000\u00bd\u00bf\u0005\u0013\u0000\u0000\u00be"+
		"\u00bd\u0001\u0000\u0000\u0000\u00be\u00bf\u0001\u0000\u0000\u0000\u00bf"+
		"\u00c4\u0001\u0000\u0000\u0000\u00c0\u00c2\u0005\u0014\u0000\u0000\u00c1"+
		"\u00c0\u0001\u0000\u0000\u0000\u00c1\u00c2\u0001\u0000\u0000\u0000\u00c2"+
		"\u00c4\u0001\u0000\u0000\u0000\u00c3\u00be\u0001\u0000\u0000\u0000\u00c3"+
		"\u00c1\u0001\u0000\u0000\u0000\u00c4\u0013\u0001\u0000\u0000\u0000\u00c5"+
		"\u00c6\u0005\u0003\u0000\u0000\u00c6\u00cd\u0005\n\u0000\u0000\u00c7\u00c9"+
		"\u0005\u0013\u0000\u0000\u00c8\u00c7\u0001\u0000\u0000\u0000\u00c8\u00c9"+
		"\u0001\u0000\u0000\u0000\u00c9\u00ce\u0001\u0000\u0000\u0000\u00ca\u00cc"+
		"\u0005\u0014\u0000\u0000\u00cb\u00ca\u0001\u0000\u0000\u0000\u00cb\u00cc"+
		"\u0001\u0000\u0000\u0000\u00cc\u00ce\u0001\u0000\u0000\u0000\u00cd\u00c8"+
		"\u0001\u0000\u0000\u0000\u00cd\u00cb\u0001\u0000\u0000\u0000\u00ce\u00cf"+
		"\u0001\u0000\u0000\u0000\u00cf\u00d6\u0003\u0018\f\u0000\u00d0\u00d2\u0005"+
		"\u0013\u0000\u0000\u00d1\u00d0\u0001\u0000\u0000\u0000\u00d1\u00d2\u0001"+
		"\u0000\u0000\u0000\u00d2\u00d7\u0001\u0000\u0000\u0000\u00d3\u00d5\u0005"+
		"\u0014\u0000\u0000\u00d4\u00d3\u0001\u0000\u0000\u0000\u00d4\u00d5\u0001"+
		"\u0000\u0000\u0000\u00d5\u00d7\u0001\u0000\u0000\u0000\u00d6\u00d1\u0001"+
		"\u0000\u0000\u0000\u00d6\u00d4\u0001\u0000\u0000\u0000\u00d7\u0015\u0001"+
		"\u0000\u0000\u0000\u00d8\u00d9\u0005\u0004\u0000\u0000\u00d9\u00e0\u0005"+
		"\n\u0000\u0000\u00da\u00dc\u0005\u0013\u0000\u0000\u00db\u00da\u0001\u0000"+
		"\u0000\u0000\u00db\u00dc\u0001\u0000\u0000\u0000\u00dc\u00e1\u0001\u0000"+
		"\u0000\u0000\u00dd\u00df\u0005\u0014\u0000\u0000\u00de\u00dd\u0001\u0000"+
		"\u0000\u0000\u00de\u00df\u0001\u0000\u0000\u0000\u00df\u00e1\u0001\u0000"+
		"\u0000\u0000\u00e0\u00db\u0001\u0000\u0000\u0000\u00e0\u00de\u0001\u0000"+
		"\u0000\u0000\u00e1\u00e2\u0001\u0000\u0000\u0000\u00e2\u00e9\u0005%\u0000"+
		"\u0000\u00e3\u00e5\u0005\u0013\u0000\u0000\u00e4\u00e3\u0001\u0000\u0000"+
		"\u0000\u00e4\u00e5\u0001\u0000\u0000\u0000\u00e5\u00ea\u0001\u0000\u0000"+
		"\u0000\u00e6\u00e8\u0005\u0014\u0000\u0000\u00e7\u00e6\u0001\u0000\u0000"+
		"\u0000\u00e7\u00e8\u0001\u0000\u0000\u0000\u00e8\u00ea\u0001\u0000\u0000"+
		"\u0000\u00e9\u00e4\u0001\u0000\u0000\u0000\u00e9\u00e7\u0001\u0000\u0000"+
		"\u0000\u00ea\u0017\u0001\u0000\u0000\u0000\u00eb\u00f6\u0005%\u0000\u0000"+
		"\u00ec\u00ee\u0005%\u0000\u0000\u00ed\u00ef\u0005\t\u0000\u0000\u00ee"+
		"\u00ed\u0001\u0000\u0000\u0000\u00ee\u00ef\u0001\u0000\u0000\u0000\u00ef"+
		"\u00f1\u0001\u0000\u0000\u0000\u00f0\u00ec\u0001\u0000\u0000\u0000\u00f1"+
		"\u00f4\u0001\u0000\u0000\u0000\u00f2\u00f0\u0001\u0000\u0000\u0000\u00f2"+
		"\u00f3\u0001\u0000\u0000\u0000\u00f3\u00f6\u0001\u0000\u0000\u0000\u00f4"+
		"\u00f2\u0001\u0000\u0000\u0000\u00f5\u00eb\u0001\u0000\u0000\u0000\u00f5"+
		"\u00f2\u0001\u0000\u0000\u0000\u00f6\u0019\u0001\u0000\u0000\u0000\u00f7"+
		"\u00f8\u0007\u0001\u0000\u0000\u00f8\u001b\u0001\u0000\u0000\u0000\u00f9"+
		"\u00fa\u0007\u0002\u0000\u0000\u00fa\u001d\u0001\u0000\u0000\u00000$\'"+
		"*-15ADHUYgjmosvx|\u008a\u008e\u00a2\u00a5\u00a7\u00ab\u00ae\u00b0\u00b5"+
		"\u00b8\u00ba\u00be\u00c1\u00c3\u00c8\u00cb\u00cd\u00d1\u00d4\u00d6\u00db"+
		"\u00de\u00e0\u00e4\u00e7\u00e9\u00ee\u00f2\u00f5";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}