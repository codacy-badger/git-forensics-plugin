package io.jenkins.plugins.git.forensics;

import java.io.IOException;

import org.eclipse.jgit.lib.ObjectId;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.lib.RepositoryBuilder;
import org.junit.Before;
import org.junit.Rule;

import jenkins.plugins.git.GitSampleRepoRule;

/**
 * Base class for Git integration tests. Provides a Git repository that will b e initialized for each test.
 *
 * @author Ullrich Hafner
 */
public class GitITest {
    /** Git repository in a temporary folder. */
    @Rule
    public GitSampleRepoRule sampleRepo = new GitSampleRepoRule();

    /**
     * Initializes the Git repository.
     *
     * @throws Exception
     *         if the initialization fails
     */
    @Before
    @SuppressWarnings("IllegalCatch")
    public void init() throws Exception {
        sampleRepo.init();
    }

    /**
     * Returns the object ID of the current HEAD commit.
     *
     * @return the head commit
     */
    @SuppressWarnings("IllegalCatch")
    protected ObjectId getHeadCommit() {
        try {
            return ObjectId.fromString(getHead());
        }
        catch (Exception exception) {
            throw new AssertionError(exception);
        }
    }

    /**
     * Returns the commit ID of the current HEAD commit.
     *
     * @return the head commit (as String)
     */
    @SuppressWarnings("IllegalCatch")
    protected String getHead() {
        try {
            return sampleRepo.head();
        }
        catch (Exception exception) {
            throw new AssertionError(exception);
        }
    }

    /**
     * Returns the sample repository.
     *
     * @return the sample repository
     */
    protected Repository createRepository() {
        try {
            return new RepositoryBuilder().setWorkTree(sampleRepo.getRoot()).build();
        }
        catch (IOException exception) {
            throw new AssertionError(exception);
        }
    }

    /**
     * Writes a file to repository.
     *
     * @param relativePath
     *         relative path ({@code /}-separated)
     * @param content
     *         the text content of the file
     */
    protected void writeFile(final String relativePath, final String content) {
        try {
            sampleRepo.write(relativePath, content);
        }
        catch (IOException exception) {
            throw new AssertionError(exception);
        }
    }

    /**
     * Writes a file to repository.
     *
     * @param commands
     *         git commands and parameters
     */
    @SuppressWarnings("IllegalCatch")
    protected void git(final String... commands) {
        try {
            sampleRepo.git(commands);
        }
        catch (Exception exception) {
            throw new AssertionError(exception);
        }
    }
}
