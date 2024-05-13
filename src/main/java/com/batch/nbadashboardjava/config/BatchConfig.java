package com.batch.nbadashboardjava.config;


import com.batch.nbadashboardjava.model.Matches;
import com.batch.nbadashboardjava.model.Teams;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
public class BatchConfig {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private PlatformTransactionManager transactionManager;
    @Bean
    public FlatFileItemReader<Matches> reader(){
        FlatFileItemReader<Matches> reader = new FlatFileItemReader<>();

        reader.setResource(new ClassPathResource("games2004.csv"));
        reader.setLineMapper(getLineMapper());

        reader.setLinesToSkip(1);

        return reader;
    }

    @Bean
    public TaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(10);
        executor.setMaxPoolSize(20);
        executor.setQueueCapacity(500);
        executor.setThreadNamePrefix("Batch-");
        executor.initialize();
        return executor;
    }

    @Bean
    public FlatFileItemReader<Teams> teamReader(){
       FlatFileItemReader<Teams> reader = new FlatFileItemReader<>();
       reader.setResource(new ClassPathResource("teamdata2004.csv"));
       reader.setLineMapper(getTeamLineMapper());

       reader.setLinesToSkip(1);

       return reader;
    }

    private LineMapper<Teams> getTeamLineMapper() {
        DefaultLineMapper<Teams> lineMapper = new DefaultLineMapper<>();

        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();

        String [] fieldNames = new String[]{"teamId", "teamName", "totalMatches", "totalWins"};

        lineTokenizer.setNames(fieldNames);

        int [] fieldNumbers = new int[]{0, 1, 2, 3};

        lineTokenizer.setIncludedFields(fieldNumbers);

        BeanWrapperFieldSetMapper<Teams> fieldSetMapper = new BeanWrapperFieldSetMapper<>();

        fieldSetMapper.setTargetType(Teams.class);
        lineMapper.setLineTokenizer(lineTokenizer);
        lineMapper.setFieldSetMapper(fieldSetMapper);

        return lineMapper;
    }

    private LineMapper<Matches> getLineMapper() {
        DefaultLineMapper<Matches> lineMapper =  new DefaultLineMapper<>();

        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();

        String [] fieldNames = new String[]{"date", "game_id", "status", "team1", "team2", "season", "ptsHome", "fgHome", "ftHome", "f3Home", "astHome", "rebHome",  "ptsAway", "fgAway", "ftAway", "f3Away", "astAway", "rebAway", "winner"};
        lineTokenizer.setNames(fieldNames);

        int [] fieldNumbers = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18};
        lineTokenizer.setIncludedFields(fieldNumbers);

        BeanWrapperFieldSetMapper<Matches> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
        fieldSetMapper.setTargetType(Matches.class);
        lineMapper.setLineTokenizer(lineTokenizer);
        lineMapper.setFieldSetMapper(fieldSetMapper);

        return lineMapper;

    }

    @Bean
    public MatchesItemProcessor processor(){
        return new MatchesItemProcessor();
    }

    @Bean
    public TeamsItemProcessor teamProcessor(){
        return new TeamsItemProcessor();
    }
    @Bean
    public JdbcBatchItemWriter<Matches> writer(){
        JdbcBatchItemWriter<Matches> writer = new JdbcBatchItemWriter<>();

        writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<Matches>());

        writer.setSql("INSERT INTO nba_matches(date, game_id, status, team1, team2, season, pts_home, fg_home, ft_home, f3Home, ast_home, reb_home, pts_away, fg_away, ft_away, f3Away, ast_away, reb_away, winner) VALUES (:date, :game_id, :status, :team1, :team2, :season, :ptsHome, :fgHome, :ftHome, :f3Home, :astHome, :rebHome, :ptsAway, :fgAway, :ftAway, :f3Away, :astAway, :rebAway, :winner)");

        writer.setDataSource(this.dataSource);

        return writer;
    }

    @Bean
    public JdbcBatchItemWriter<Teams> teamWriter(){
        JdbcBatchItemWriter<Teams> writer = new JdbcBatchItemWriter<>();

        writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<Teams>());

        writer.setSql("INSERT INTO nba_teams(team_id, team_name, total_matches, total_wins) VALUES (:teamId, :teamName, :totalMatches, :totalWins)");

        writer.setDataSource(this.dataSource);

        return writer;
    }

    @Bean
    public Step step2() {
        return new StepBuilder("step2", jobRepository)
                .<Teams, Teams>chunk(10, transactionManager)
                .reader(teamReader())
                .processor(teamProcessor())
                .writer(teamWriter())
                .taskExecutor(taskExecutor())
                .build();
    }

    @Bean
    public Job importMatchesJob() {
        return new JobBuilder("MATCHES-IMPORT-JOB", jobRepository)
                .incrementer(new RunIdIncrementer())
                .start(step2())
                .next(step1())
                .build();
    }


    @Bean
    public Step step1() {
        return new StepBuilder("step1", jobRepository).
                <Matches, Matches>chunk(10, transactionManager).
                reader(reader()).
                processor(processor()).
                writer(writer()).
                taskExecutor(taskExecutor())
                .build();
    }

}
